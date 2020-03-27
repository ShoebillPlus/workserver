package com.xr.bgmt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.bgmt.DAO.BgmtKpiApiMapper;
import com.xr.bgmt.DAO.WxKpiApiMapper;
import com.xr.bgmt.entity.SysUser;
import com.xr.bgmt.entity.form.BgmtKpiRet;
import com.xr.bgmt.entity.form.SysUserForm;
import com.xr.bgmt.entity.form.WsKpiScoreRetForm;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.BgmtKpiApiService;
import com.xr.bgmt.service.SysUserService;
import com.xr.bgmt.utils.ExcelUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台接口 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@Service
public class BgmtKpiApiServiceImpl extends ServiceImpl<BgmtKpiApiMapper, WsKpiScoreRetForm> implements BgmtKpiApiService {

    private static final Logger logger = LoggerFactory.getLogger(BgmtKpiApiServiceImpl.class);

    @Resource
    BgmtKpiApiMapper bgmtKpiApiMapper;

    private SysUserService sysUserService;

    @Resource
    private WxKpiApiMapper wxKpiApiMapper;

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }


    @Override
    public IPage<WsKpiScoreRetForm> kpiDetailPage(String month,Pageable pageable) throws ApiException {
        IPage<WsKpiScoreRetForm> wsKpiScoreRetFormIPage;
        try {
            Page<WsKpiScoreRetForm> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            wsKpiScoreRetFormIPage = bgmtKpiApiMapper.kpiDetailPage(month,page);
            logger.debug("查询考评详细列表成功");
        } catch (Exception e) {
            logger.error("查询考评详细列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询考评详细列表异常", HttpStatus.BAD_REQUEST);
        }
        return wsKpiScoreRetFormIPage;
    }

    @Override
    public IPage<BgmtKpiRet> kpiRet(String month, Pageable pageable) throws ApiException {
        IPage<BgmtKpiRet> wsKpiScoreRetFormIPage;
        try {
            Page<BgmtKpiRet> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            wsKpiScoreRetFormIPage = bgmtKpiApiMapper.BgmtKpiRet(1,page);
            List<BgmtKpiRet> bgmtKpiRetList = wsKpiScoreRetFormIPage.getRecords();
            for(int i = 0;i<bgmtKpiRetList.size();i++){
                BgmtKpiRet bgmtKpiRet = bgmtKpiRetList.get(i);
                // 获取所有考评人
                int noAssessNum = 0;
                int assessNum = 0;
                Double totalScore = 0D;
                List<SysUserForm> sysUserFormList = wxKpiApiMapper.getAssessederList(bgmtKpiRet.getAssessedId(),"1");
                // 判断是否考评
                for(int j = 0;j<sysUserFormList.size();j++){
                    // 获取month下的数据
                    WsKpiScoreRetForm wsKpiScoreRetForm = wxKpiApiMapper.getAssessederRet(month, sysUserFormList.get(j).getId(), bgmtKpiRetList.get(i).getAssessedId());
                    if(wsKpiScoreRetForm==null){
                        noAssessNum = noAssessNum + 1;
                    }else{
                        assessNum = assessNum + 1;
                        totalScore = totalScore+wsKpiScoreRetForm.getTotalScore();
                    }
                }
                bgmtKpiRet.setNoAssessNum(noAssessNum);
                bgmtKpiRet.setAssessNum(assessNum);
                if(assessNum<=0){
                    bgmtKpiRet.setAvgScore("0.00");
                }else{
                    DecimalFormat df=new DecimalFormat("0.00");
                    System.out.println(totalScore/assessNum);
                    bgmtKpiRet.setAvgScore(df.format(totalScore/assessNum));
                    System.out.println(df.format(totalScore/assessNum));
                }

                bgmtKpiRetList.set(i,bgmtKpiRet);
            }
            wsKpiScoreRetFormIPage.setRecords(bgmtKpiRetList);
            logger.debug("查询考评结果列表成功");
        } catch (Exception e) {
            logger.error("查询考评结果列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询考评结果列表异常", HttpStatus.BAD_REQUEST);
        }
        return wsKpiScoreRetFormIPage;
    }

    @Override
    public void kpiRetExport(String month, Pageable pageable, HttpServletResponse response) throws ApiException {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            IPage<BgmtKpiRet> wsKpiScoreRetFormIPage = kpiRet(month,pageable);
            Map dataMap = new HashMap();
            List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
            for(int i = 0 ;i<wsKpiScoreRetFormIPage.getRecords().size();i++){
                Map<String, Object>map=new HashMap<String, Object>();
                map.put("deptName", wsKpiScoreRetFormIPage.getRecords().get(i).getDeptName());
                map.put("assessedName",  wsKpiScoreRetFormIPage.getRecords().get(i).getAssessedName());
                map.put("noAssessNum",  wsKpiScoreRetFormIPage.getRecords().get(i).getNoAssessNum());
                map.put("assessNum",  wsKpiScoreRetFormIPage.getRecords().get(i).getAssessNum());
                map.put("avgScore",  wsKpiScoreRetFormIPage.getRecords().get(i).getAvgScore());
                list.add(map);
            }
            dataMap.put("BgmtKpiRetList", list);
            dataMap.put("ftl", "result.ftl");
            ExcelUtil excelUtil = new ExcelUtil();
            String filePath = "D://result.xls";
            excelUtil.print(dataMap,filePath);

            File newFile = new File(filePath);
            inputStream = new FileInputStream(newFile);
            outputStream = response.getOutputStream();
            String suffix = filePath.substring(filePath.lastIndexOf("."), filePath.length());
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(month.getBytes("UTF-8"), "ISO-8859-1") + suffix);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }catch (Exception e){
            logger.error("导出考评结果Excel异常", e);
            e.printStackTrace();
            throw new ApiException("导出考评结果Excel异常", HttpStatus.BAD_REQUEST);
        }
    }
}

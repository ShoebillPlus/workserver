package com.xr.bgmt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xr.bgmt.DAO.WsKpiCriterionMapper;
import com.xr.bgmt.DAO.WsKpiLevelMapper;
import com.xr.bgmt.DAO.WsKpiRuleMapper;
import com.xr.bgmt.DAO.WxKpiApiMapper;
import com.xr.bgmt.entity.*;
import com.xr.bgmt.entity.form.SysUserForm;
import com.xr.bgmt.entity.form.WsKpiScoreRetForm;
import com.xr.bgmt.entity.form.WxKpiRet;
import com.xr.bgmt.entity.form.WxKpiRuleForm;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WxKpiApiServiceImpl implements WxKpiApiService {

    private static final Logger logger = LoggerFactory.getLogger(WxKpiApiServiceImpl.class);

    @Value("${wx-config.login-url}")
    private String loginUrl;
    @Value("${wx-config.appid}")
    private String appid;
    @Value("${wx-config.secret}")
    private String secret;
    @Value("${user-auth-server-url}")
    private String authServerUrl;
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientScret;

    private SysUserService sysUserService;

    @Resource
    private WxKpiApiMapper wxKpiApiMapper;

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    private SysParamService sysParamService;

    @Autowired
    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    private WsKpiScoreRecordService wsKpiScoreRecordService;

    @Autowired
    public void setWsKpiScoreRecordService(WsKpiScoreRecordService wsKpiScoreRecordService) {
        this.wsKpiScoreRecordService = wsKpiScoreRecordService;
    }

    private WsKpiScoreRetService wsKpiScoreRetService;

    @Autowired
    public void setWsKpiScoreRetService(WsKpiScoreRetService wsKpiScoreRetService) {
        this.wsKpiScoreRetService = wsKpiScoreRetService;
    }

    @Resource
    private WsKpiCriterionMapper wsKpiCriterionMapper;

    @Resource
    private WsKpiLevelMapper wsKpiLevelMapper;


    // 获取token
    @Override
    public Map<String, Object> getAssesseder(String assessorId) throws ApiException {
        Map<String, Object> map = new HashMap<>();
        // 获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        String month = df.format(date);
        map.put("month", month);
        List<SysUserForm> retList = new ArrayList<>();
        try {
            List<SysUserForm> sysUserFormList = wxKpiApiMapper.getAssessederList(assessorId,"0",month);
            for (SysUserForm sysUserForm : sysUserFormList) {
                WsKpiScoreRetForm wsKpiScoreRetForm = wxKpiApiMapper.getAssessederRet(month, assessorId, sysUserForm.getId());
                if (wsKpiScoreRetForm == null) {
                    sysUserForm.setIsAssess(false);
                } else {
                    sysUserForm.setIsAssess(true);
                    sysUserForm.setTotalScore(wsKpiScoreRetForm.getTotalScore());
                    sysUserForm.setRet(wsKpiScoreRetForm.getRet());
                }
                retList.add(sysUserForm);
            }
            map.put("list", retList);
        } catch (Exception e) {
            logger.error("查询待评人员信息列表失败", e);
            e.printStackTrace();
            throw new ApiException("查询待评人员信息列表错误", HttpStatus.BAD_REQUEST);
        }
        return map;
    }

    @Override
    public WsKpiRule getKpiRule(String assessedId) throws ApiException {
        WsKpiRule wsKpiRule;
        try {
            wsKpiRule = wxKpiApiMapper.getKpiRule(assessedId);
            QueryWrapper<WsKpiCriterion> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("rule_id",wsKpiRule.getId());
            queryWrapper.orderByAsc("sort");
            wsKpiRule.setWsKpiCriterionList(wsKpiCriterionMapper.selectList(queryWrapper));
            for(int i = 0;i< wsKpiRule.getWsKpiCriterionList().size();i++){
                WsKpiCriterion wsKpiCriterion = wsKpiRule.getWsKpiCriterionList().get(i);
                QueryWrapper<WsKpiLevel> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("criterion_id",wsKpiCriterion.getId());
                wsKpiCriterion.setWsKpiLevelList(wsKpiLevelMapper.selectList(queryWrapper1));
                wsKpiRule.getWsKpiCriterionList().set(i,wsKpiCriterion);
            }
        } catch (Exception e) {
            logger.error("查询待评人员信息列表失败", e);
            e.printStackTrace();
            throw new ApiException("查询待评人员信息列表错误", HttpStatus.BAD_REQUEST);
        }
        return wsKpiRule;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitRet(WxKpiRet wxKpiRet) throws ApiException {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date); // 设置为当前时间
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
            date = calendar.getTime();
            String month = df.format(date);
            wxKpiRet.setMonth(month);
            Double totalScore = 0D;
            WsKpiScoreRet wsKpiScoreRet = JSONObject.toJavaObject((JSON)JSON.toJSON(wxKpiRet),WsKpiScoreRet.class);
            WsKpiScoreRecord wsKpiScoreRecord = JSONObject.toJavaObject((JSON)JSON.toJSON(wxKpiRet),WsKpiScoreRecord.class);
            SimpleDateFormat df1 = new SimpleDateFormat("d");
            for(int i =0;i<wxKpiRet.getWxKpiRetDetailList().size();i++){
                wsKpiScoreRecord.setLevelId(wxKpiRet.getWxKpiRetDetailList().get(i).getLevelId());

                wsKpiScoreRecord.setMonth(month);
                wsKpiScoreRecord.setDay(df1.format(date));
                wsKpiScoreRecord.setScore(wxKpiRet.getWxKpiRetDetailList().get(i).getScore());
                wsKpiScoreRecordService.add(wsKpiScoreRecord);
                totalScore = totalScore + wxKpiRet.getWxKpiRetDetailList().get(i).getScore();
        }
            wsKpiScoreRet.setDay(df1.format(date));
            wsKpiScoreRet.setTotalScore(totalScore);
            wsKpiScoreRetService.add(wsKpiScoreRet);
            logger.debug("提交考评结果成功" );
        } catch (ApiException e) {
            logger.error("提交考评结果错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("提交考评结果异常", e1);
            e1.printStackTrace();
            throw new ApiException("提交考评结果异常", HttpStatus.BAD_REQUEST);
        }
    }
}

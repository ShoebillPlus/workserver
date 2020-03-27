package com.xr.bgmt.service.impl;

import com.xr.bgmt.entity.SysDict;
import com.xr.bgmt.DAO.SysDictMapper;
import com.xr.bgmt.service.SysDictItemService;
import com.xr.bgmt.service.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.xr.bgmt.utils.HcUtil;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-23
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private static final Logger logger = LoggerFactory.getLogger(SysDictServiceImpl.class);

    private SysDictItemService sysDictItemService;

    @Autowired
    public void setSysDictItemService(SysDictItemService sysDictItemService) {
        this.sysDictItemService = sysDictItemService;
    }

    @Resource
    SysDictMapper sysDictMapper;

    @Override
    public IPage<SysDict> findPage(Pageable pageable) throws ApiException {
        IPage<SysDict> retPage;
        try {
            Page<SysDict> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            // 循环查询对应字典项列表
            List<SysDict> sysDictList = retPage.getRecords();
            for(int i = 0;i<sysDictList.size();i++){
                SysDict sysDict = sysDictList.get(i);
                sysDict.setSysDictItemList(sysDictItemService.findByDictCode(sysDict.getCode()));
                sysDictList.set(i,sysDict);
            }
            retPage.setRecords(sysDictList);
            logger.debug("查询字典表列表成功");
        } catch (Exception e) {
            logger.error("查询字典表列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询字典表列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysDict sysDict) throws ApiException {
        try {
        this.save(sysDict);
        logger.debug("添加字典表成功" + sysDict.getId());
        } catch (ApiException e) {
            logger.error("添加字典表错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加字典表异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加字典表异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除字典表成功" + id);
        } catch (Exception e) {
            logger.error("删除字典表异常", e);
            e.printStackTrace();
            throw new ApiException("删除字典表异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(SysDict sysDict) throws ApiException {
        try {
            UpdateWrapper<SysDict> wrapper = new UpdateWrapper();
            wrapper.eq("id",sysDict.getId());
            this.update(sysDict,wrapper);
            logger.debug("更新字典表成功" + sysDict.getId());
        } catch (ApiException e) {
            logger.error("更新字典表错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新字典表异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新字典表异常", HttpStatus.BAD_REQUEST);
        }
    }

}

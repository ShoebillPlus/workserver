package com.xr.bgmt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xr.bgmt.entity.SysParam;
import com.xr.bgmt.DAO.SysParamMapper;
import com.xr.bgmt.service.SysParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.utils.HcUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;
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

import java.util.Date;

/**
 * <p>
 * 系统参数 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@Service
public class SysParamServiceImpl extends ServiceImpl<SysParamMapper, SysParam> implements SysParamService {

    private static final Logger logger = LoggerFactory.getLogger(SysParamServiceImpl.class);

    @Resource
    SysParamMapper sysParamMapper;

    @Override
    public IPage<SysParam> findPage(Pageable pageable) throws ApiException {
        IPage<SysParam> retPage;
        try {
            Page<SysParam> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询系统参数列表成功");
        } catch (Exception e) {
            logger.error("查询系统参数列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询系统参数列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysParam sysParam) throws ApiException {
        try {
            if (StringUtils.isBlank(sysParam.getId())) {
                sysParam.setId(HcUtil.getUUID(true));
            }
            sysParam.setCreateTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            this.save(sysParam);
            logger.debug("添加系统参数成功" + sysParam.getId());
        } catch (ApiException e) {
            logger.error("添加系统参数错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加系统参数异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加系统参数异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除系统参数成功" + id);
        } catch (Exception e) {
            logger.error("删除系统参数异常", e);
            e.printStackTrace();
            throw new ApiException("删除系统参数异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(SysParam sysParam) throws ApiException {
        try {
            if (StringUtils.isBlank(sysParam.getId())) {
                throw new ApiException("编号不能为空", HttpStatus.BAD_REQUEST);
            }
            UpdateWrapper<SysParam> wrapper = new UpdateWrapper();
            wrapper.eq("id",sysParam.getId());
            sysParam.setUpdateTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            this.update(sysParam,wrapper);
            logger.debug("更新系统参数成功" + sysParam.getId());
        } catch (ApiException e) {
            logger.error("更新系统参数错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新系统参数异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新系统参数异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public SysParam findByKey(String configKey) throws ApiException {
        SysParam sysParam;
        try {
            QueryWrapper<SysParam> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("config_key",configKey);
            sysParam = this.getOne(queryWrapper);
            logger.debug("查询系统参数成功");
        } catch (Exception e) {
            logger.error("查询系统参数异常", e);
            e.printStackTrace();
            throw new ApiException("查询系统参数异常", HttpStatus.BAD_REQUEST);
        }
        return sysParam;
    }
}

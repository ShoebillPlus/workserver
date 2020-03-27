package com.xr.bgmt.service.impl;

import com.xr.bgmt.entity.SysRole;
import com.xr.bgmt.DAO.SysRoleMapper;
import com.xr.bgmt.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Resource
    SysRoleMapper sysRoleMapper;

    @Override
    public IPage<SysRole> findPage(Pageable pageable) throws ApiException {
        IPage<SysRole> retPage;
        try {
            Page<SysRole> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询列表成功");
        } catch (Exception e) {
            logger.error("查询列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysRole sysRole) throws ApiException {
        try {
        this.save(sysRole);
        logger.debug("添加成功" + sysRole.getId());
        } catch (ApiException e) {
            logger.error("添加错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除成功" + id);
        } catch (Exception e) {
            logger.error("删除异常", e);
            e.printStackTrace();
            throw new ApiException("删除异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(SysRole sysRole) throws ApiException {
        try {
            UpdateWrapper<SysRole> wrapper = new UpdateWrapper();
            wrapper.eq("id",sysRole.getId());
            this.update(sysRole,wrapper);
            logger.debug("更新成功" + sysRole.getId());
        } catch (ApiException e) {
            logger.error("更新错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新异常", HttpStatus.BAD_REQUEST);
        }
    }

}

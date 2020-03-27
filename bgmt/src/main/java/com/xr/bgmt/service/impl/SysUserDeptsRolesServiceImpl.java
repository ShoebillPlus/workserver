package com.xr.bgmt.service.impl;

import com.xr.bgmt.entity.SysUserDeptsRoles;
import com.xr.bgmt.DAO.SysUserDeptsMapper;
import com.xr.bgmt.entity.form.UserDeptsRoles;
import com.xr.bgmt.service.SysUserDeptsRolesService;
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

import java.util.List;

/**
 * <p>
 * 用户部门 服务实现类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Service
public class SysUserDeptsRolesServiceImpl extends ServiceImpl<SysUserDeptsMapper, SysUserDeptsRoles> implements SysUserDeptsRolesService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserDeptsRolesServiceImpl.class);

    @Resource
    SysUserDeptsMapper sysUserDeptsMapper;

    @Override
    public IPage<SysUserDeptsRoles> findPage(Pageable pageable) throws ApiException {
        IPage<SysUserDeptsRoles> retPage;
        try {
            Page<SysUserDeptsRoles> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
            retPage = this.page(page);
            logger.debug("查询用户部门列表成功");
        } catch (Exception e) {
            logger.error("查询用户部门列表异常", e);
            e.printStackTrace();
            throw new ApiException("查询用户部门列表异常", HttpStatus.BAD_REQUEST);
        }
        return retPage;
   }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysUserDeptsRoles sysUserDeptsRoles) throws ApiException {
        try {

            this.save(sysUserDeptsRoles);
            logger.debug("添加用户部门成功" + sysUserDeptsRoles.getUserId());
        } catch (ApiException e) {
            logger.error("添加用户部门错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加用户部门异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加用户部门异常", HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addList(UserDeptsRoles userDeptsRoles) throws ApiException {
        try {
            if(userDeptsRoles.getUserId()==null){
                throw new ApiException("用户编号不能为空", HttpStatus.BAD_REQUEST);
            }
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("user_id",userDeptsRoles.getUserId());
            sysUserDeptsMapper.delete(updateWrapper);
            for(int i =0;i<userDeptsRoles.getDeptsRolesList().size();i++){
                SysUserDeptsRoles sysUserDeptsRoles = new SysUserDeptsRoles();
                sysUserDeptsRoles.setUserId(userDeptsRoles.getUserId());
                sysUserDeptsRoles.setDeptId(userDeptsRoles.getDeptsRolesList().get(i).getDeptId());
                sysUserDeptsRoles.setRoleId(userDeptsRoles.getDeptsRolesList().get(i).getRoleId());
                this.save(sysUserDeptsRoles);
            }
            logger.debug("添加用户部门成功" + userDeptsRoles.getUserId());
        } catch (ApiException e) {
            logger.error("添加用户部门错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("添加用户部门异常", e1);
            e1.printStackTrace();
            throw new ApiException("添加用户部门异常", HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws ApiException {
        try {
            this.removeById(id);
            logger.debug("删除用户部门成功" + id);
        } catch (Exception e) {
            logger.error("删除用户部门异常", e);
            e.printStackTrace();
            throw new ApiException("删除用户部门异常", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refresh(SysUserDeptsRoles sysUserDeptsRoles) throws ApiException {
        try {
            UpdateWrapper<SysUserDeptsRoles> wrapper = new UpdateWrapper();
            wrapper.eq("id", sysUserDeptsRoles.getUserId());
            this.update(sysUserDeptsRoles,wrapper);
            logger.debug("更新用户部门成功" + sysUserDeptsRoles.getUserId());
        } catch (ApiException e) {
            logger.error("更新用户部门错误:" + e.getMessage(), e);
            throw e;
        } catch (Exception e1) {
            logger.error("更新用户部门异常", e1);
            e1.printStackTrace();
            throw new ApiException("更新用户部门异常", HttpStatus.BAD_REQUEST);
        }
    }

}

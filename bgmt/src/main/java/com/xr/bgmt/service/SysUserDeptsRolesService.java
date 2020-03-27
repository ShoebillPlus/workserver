package com.xr.bgmt.service;

import com.xr.bgmt.entity.SysUserDeptsRoles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.entity.form.UserDeptsRoles;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 用户部门 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface SysUserDeptsRolesService extends IService<SysUserDeptsRoles> {

      /**
       * 根据条件分页查询用户部门
       *
       * @param pageable 分页对象
       * @return IPage<SysUserDeptsRoles>   用户部门列表（分页）
       */
       IPage<SysUserDeptsRoles> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加用户部门
       *
       * @param sysUserDeptsRoles 用户部门
       * @throws ApiException 异常信息
       */
      void add(SysUserDeptsRoles sysUserDeptsRoles) throws ApiException;

      void addList(UserDeptsRoles userDeptsRoles) throws ApiException;


      /**
      * 删除用户部门
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新用户部门
      *
      * @param sysUserDeptsRoles 用户部门
      * @throws ApiException 异常信息
      */
      void refresh(SysUserDeptsRoles sysUserDeptsRoles) throws ApiException;

}

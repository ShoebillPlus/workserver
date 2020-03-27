package com.xr.bgmt.service;

import com.xr.bgmt.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface SysRoleService extends IService<SysRole> {
      /**
       * 根据条件分页查询
       *
       * @param pageable 分页对象
       * @return IPage<SysRole>   列表（分页）
       */
       IPage<SysRole> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加
       *
       * @param sysRole 
       * @throws ApiException 异常信息
       */
      void add(SysRole sysRole) throws ApiException;

      /**
      * 删除
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新
      *
      * @param sysRole 
      * @throws ApiException 异常信息
      */
      void refresh(SysRole sysRole) throws ApiException;

}

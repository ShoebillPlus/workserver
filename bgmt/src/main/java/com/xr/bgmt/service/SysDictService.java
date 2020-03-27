package com.xr.bgmt.service;

import com.xr.bgmt.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-23
 */
public interface SysDictService extends IService<SysDict> {
      /**
       * 根据条件分页查询字典表
       *
       * @param pageable 分页对象
       * @return IPage<SysDict>   字典表列表（分页）
       */
       IPage<SysDict> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加字典表
       *
       * @param sysDict 字典表
       * @throws ApiException 异常信息
       */
      void add(SysDict sysDict) throws ApiException;

      /**
      * 删除字典表
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新字典表
      *
      * @param sysDict 字典表
      * @throws ApiException 异常信息
      */
      void refresh(SysDict sysDict) throws ApiException;

}

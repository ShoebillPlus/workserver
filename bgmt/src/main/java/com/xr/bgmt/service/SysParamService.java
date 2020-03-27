package com.xr.bgmt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.entity.SysParam;
import com.xr.bgmt.exception.ApiException;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * 系统参数 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
public interface SysParamService extends IService<SysParam> {
      /**
       * 根据条件分页查询系统参数
       *
       * @param pageable 分页对象
       * @return IPage<SysParam>   系统参数列表（分页）
       */
       IPage<SysParam> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加系统参数
       *
       * @param sysParam 系统参数
       * @throws ApiException 异常信息
       */
      void add(SysParam sysParam) throws ApiException;

      /**
      * 删除系统参数
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新系统参数
      *
      * @param sysParam 系统参数
      * @throws ApiException 异常信息
      */
      void refresh(SysParam sysParam) throws ApiException;

      /**
       * 根据配置键查询系统参数
       *
       * @param configKey 配置键
       * @return SysParam   系统参数
       */
      SysParam findByKey(String configKey) throws ApiException;

}

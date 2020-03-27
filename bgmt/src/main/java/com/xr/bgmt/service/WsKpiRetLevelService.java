package com.xr.bgmt.service;

import com.xr.bgmt.entity.WsKpiRetLevel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * 得分结果等级 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface WsKpiRetLevelService extends IService<WsKpiRetLevel> {
      /**
       * 根据条件分页查询得分结果等级
       *
       * @param pageable 分页对象
       * @return IPage<WsKpiRetLevel>   得分结果等级列表（分页）
       */
       IPage<WsKpiRetLevel> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加得分结果等级
       *
       * @param wsKpiRetLevel 得分结果等级
       * @throws ApiException 异常信息
       */
      void add(WsKpiRetLevel wsKpiRetLevel) throws ApiException;

      /**
      * 删除得分结果等级
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新得分结果等级
      *
      * @param wsKpiRetLevel 得分结果等级
      * @throws ApiException 异常信息
      */
      void refresh(WsKpiRetLevel wsKpiRetLevel) throws ApiException;

}

package com.xr.bgmt.service;

import com.xr.bgmt.entity.WsKpiCriterion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * 绩效指标 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface WsKpiCriterionService extends IService<WsKpiCriterion> {
      /**
       * 根据条件分页查询绩效指标
       *
       * @param pageable 分页对象
       * @return IPage<WsKpiCriterion>   绩效指标列表（分页）
       */
       IPage<WsKpiCriterion> findPage(Pageable pageable,String ruleId) throws ApiException;

      /**
       * 增加绩效指标
       *
       * @param wsKpiCriterion 绩效指标
       * @throws ApiException 异常信息
       */
      void add(WsKpiCriterion wsKpiCriterion) throws ApiException;

      /**
      * 删除绩效指标
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新绩效指标
      *
      * @param wsKpiCriterion 绩效指标
      * @throws ApiException 异常信息
      */
      void refresh(WsKpiCriterion wsKpiCriterion) throws ApiException;

}

package com.xr.bgmt.service;

import com.xr.bgmt.entity.WsKpiRule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * 绩效考核规则 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface WsKpiRuleService extends IService<WsKpiRule> {
      /**
       * 根据条件分页查询绩效考核规则
       *
       * @param pageable 分页对象
       * @return IPage<WsKpiRule>   绩效考核规则列表（分页）
       */
       IPage<WsKpiRule> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加绩效考核规则
       *
       * @param wsKpiRule 绩效考核规则
       * @throws ApiException 异常信息
       */
      void add(WsKpiRule wsKpiRule) throws ApiException;

      /**
      * 删除绩效考核规则
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新绩效考核规则
      *
      * @param wsKpiRule 绩效考核规则
      * @throws ApiException 异常信息
      */
      void refresh(WsKpiRule wsKpiRule) throws ApiException;

}

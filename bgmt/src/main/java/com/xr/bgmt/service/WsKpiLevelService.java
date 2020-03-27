package com.xr.bgmt.service;

import com.xr.bgmt.entity.WsKpiLevel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * 考核等级 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface WsKpiLevelService extends IService<WsKpiLevel> {
      /**
       * 根据条件分页查询考核等级
       *
       * @param pageable 分页对象
       * @return IPage<WsKpiLevel>   考核等级列表（分页）
       */
       IPage<WsKpiLevel> findPage(Pageable pageable,String ruleId,String criterionId) throws ApiException;

      /**
       * 增加考核等级
       *
       * @param wsKpiLevel 考核等级
       * @throws ApiException 异常信息
       */
      void add(WsKpiLevel wsKpiLevel) throws ApiException;

      /**
      * 删除考核等级
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新考核等级
      *
      * @param wsKpiLevel 考核等级
      * @throws ApiException 异常信息
      */
      void refresh(WsKpiLevel wsKpiLevel) throws ApiException;

}

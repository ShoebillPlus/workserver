package com.xr.bgmt.service;

import com.xr.bgmt.entity.WsKpiScoreRet;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * 得分结果 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface WsKpiScoreRetService extends IService<WsKpiScoreRet> {
      /**
       * 根据条件分页查询得分结果
       *
       * @param pageable 分页对象
       * @return IPage<WsKpiScoreRet>   得分结果列表（分页）
       */
       IPage<WsKpiScoreRet> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加得分结果
       *
       * @param wsKpiScoreRet 得分结果
       * @throws ApiException 异常信息
       */
      void add(WsKpiScoreRet wsKpiScoreRet) throws ApiException;

      /**
      * 删除得分结果
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新得分结果
      *
      * @param wsKpiScoreRet 得分结果
      * @throws ApiException 异常信息
      */
      void refresh(WsKpiScoreRet wsKpiScoreRet) throws ApiException;

}

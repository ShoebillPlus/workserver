package com.xr.bgmt.service;

import com.xr.bgmt.entity.WsKpiScoreRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * 得分记录 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface WsKpiScoreRecordService extends IService<WsKpiScoreRecord> {
      /**
       * 根据条件分页查询得分记录
       *
       * @param pageable 分页对象
       * @return IPage<WsKpiScoreRecord>   得分记录列表（分页）
       */
       IPage<WsKpiScoreRecord> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加得分记录
       *
       * @param wsKpiScoreRecord 得分记录
       * @throws ApiException 异常信息
       */
      void add(WsKpiScoreRecord wsKpiScoreRecord) throws ApiException;

      /**
      * 删除得分记录
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新得分记录
      *
      * @param wsKpiScoreRecord 得分记录
      * @throws ApiException 异常信息
      */
      void refresh(WsKpiScoreRecord wsKpiScoreRecord) throws ApiException;

}

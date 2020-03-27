package com.xr.bgmt.service;

import com.xr.bgmt.entity.SysDictItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-23
 */
public interface SysDictItemService extends IService<SysDictItem> {
      /**
       * 根据条件分页查询
       *
       * @param pageable 分页对象
       * @return IPage<SysDictItem>   列表（分页）
       */
       IPage<SysDictItem> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加
       *
       * @param sysDictItem 
       * @throws ApiException 异常信息
       */
      void add(SysDictItem sysDictItem) throws ApiException;

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
      * @param sysDictItem 
      * @throws ApiException 异常信息
      */
      void refresh(SysDictItem sysDictItem) throws ApiException;

      List<SysDictItem> findByDictCode(String dictCode) throws ApiException;

}

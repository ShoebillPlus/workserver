package com.xr.bgmt.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xr.bgmt.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * 机构部门 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface SysDeptService extends IService<SysDept> {


      JSONArray findTree() throws ApiException;

      /**
       * 根据条件分页查询机构部门
       *
       * @param pageable 分页对象
       * @return IPage<SysDept>   机构部门列表（分页）
       */
       IPage<SysDept> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加机构部门
       *
       * @param sysDept 机构部门
       * @throws ApiException 异常信息
       */
      void add(SysDept sysDept) throws ApiException;

      /**
      * 删除机构部门
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新机构部门
      *
      * @param sysDept 机构部门
      * @throws ApiException 异常信息
      */
      void refresh(SysDept sysDept) throws ApiException;

}

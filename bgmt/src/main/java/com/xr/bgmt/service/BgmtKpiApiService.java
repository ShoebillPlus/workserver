package com.xr.bgmt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.entity.SysUser;
import com.xr.bgmt.entity.form.BgmtKpiRet;
import com.xr.bgmt.entity.form.SysUserForm;
import com.xr.bgmt.entity.form.WsKpiScoreRetForm;
import com.xr.bgmt.exception.ApiException;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 后台接口 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
public interface BgmtKpiApiService extends IService<WsKpiScoreRetForm> {
      /**
       * 根据条件分页查询考评详细信息
       *
       * @param pageable 分页对象
       * @return IPage<SysUser>   人员信息列表（分页）
       */
       IPage<WsKpiScoreRetForm> kpiDetailPage(String month,Pageable pageable) throws ApiException;

        /**
         * 根据条件分页查询考评详细信息
         *
         * @param pageable 分页对象
         * @return IPage<SysUser>   人员信息列表（分页）
         */
        IPage<BgmtKpiRet> kpiRet(String month, Pageable pageable) throws ApiException;


        void kpiRetExport(String month, Pageable pageable, HttpServletResponse response) throws ApiException;

}

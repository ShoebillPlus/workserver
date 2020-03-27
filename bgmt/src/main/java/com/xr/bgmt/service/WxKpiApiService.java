package com.xr.bgmt.service;

import com.xr.bgmt.entity.WsKpiRule;
import com.xr.bgmt.entity.form.SysUserForm;
import com.xr.bgmt.entity.form.WxKpiRet;
import com.xr.bgmt.entity.form.WxKpiRuleForm;
import com.xr.bgmt.exception.ApiException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
public interface WxKpiApiService {

    // 获取待评价人列表信息
    public Map<String,Object> getAssesseder(String assessorId) throws ApiException;

    // 获取评价细则
    public WsKpiRule getKpiRule(String assessedId) throws ApiException;

    // 获取评价细则
    void submitRet(WxKpiRet wxKpiRet) throws ApiException;
}

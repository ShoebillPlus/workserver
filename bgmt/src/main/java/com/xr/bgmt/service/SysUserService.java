package com.xr.bgmt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xr.bgmt.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.entity.form.SysUserForm;
import com.xr.bgmt.exception.ApiException;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 人员信息 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-19
 */
public interface SysUserService extends IService<SysUser> {
    public IPage<SysUserForm> findPage(int paramInt, Pageable paramPageable)
            throws ApiException;

    public abstract void add(SysUser paramSysUser)
            throws ApiException;

    public void delete(String paramString)
            throws ApiException;

    public void refresh(SysUser paramSysUser)
            throws ApiException;

    public SysUser findByAccount(String paramString)
            throws ApiException;

    public SysUser findByPhone(String paramString)
            throws ApiException;

    public  SysUser findByOpenid(String paramString)
            throws ApiException;
}

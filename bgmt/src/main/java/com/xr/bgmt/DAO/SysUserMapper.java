package com.xr.bgmt.DAO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xr.bgmt.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xr.bgmt.entity.form.SysUserForm;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 人员信息 Mapper 接口
 * </p>
 *
 * @author yanwei
 * @since 2020-03-19
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    public abstract IPage<SysUserForm> findPage(@Param("type") int paramInt, IPage<SysUserForm> paramIPage);
}

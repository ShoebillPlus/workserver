package com.xr.bgmt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户部门
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user_depts_roles")
@ApiModel(value="SysUserDeptsRoles对象", description="用户部门")
public class SysUserDeptsRoles extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "user_id", value = "用户编号")
    private Long userId;

    @ApiModelProperty(name = "dept_id", value = "部门编号")
    private Long deptId;

    @ApiModelProperty(name = "role_id", value = "角色编号")
    private Long roleId;

}

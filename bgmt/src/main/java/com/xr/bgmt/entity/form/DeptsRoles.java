package com.xr.bgmt.entity.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class  DeptsRoles{
    @ApiModelProperty(name = "dept_id", value = "部门编号")
    private Long deptId;

    @ApiModelProperty(name = "role_id", value = "角色编号")
    private Long roleId;
}

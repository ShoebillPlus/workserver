package com.xr.bgmt.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xr.bgmt.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

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
@ApiModel(value="UserDeptsRoles对象", description="用户部门")
public class UserDeptsRoles{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "user_id", value = "用户编号")
    private Long userId;

    @ApiModelProperty(name = "deptsRolesList", value = "部门角色")
    private List<DeptsRoles> deptsRolesList;
}

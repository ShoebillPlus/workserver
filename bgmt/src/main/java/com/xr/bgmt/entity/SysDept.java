package com.xr.bgmt.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 机构部门
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_dept")
@ApiModel(value="SysDept对象", description="机构部门")
public class SysDept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "name", value = "部门名称")
    private String name;

    @ApiModelProperty(name = "parent_id", value = "上级编号")
    private Long parentId;

    @ApiModelProperty(name = "level", value = "部门层级")
    private Integer level;

    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;

    @ApiModelProperty(name = "is_valid", value = "是否有效（0:否；1:是）")
    @TableField(value = "is_valid", fill = FieldFill.INSERT)
    private Integer isValid;

    @ApiModelProperty(name = "sysDeptList", value = "子部门")
    @TableField(exist = false)
    private List<SysDept> sysDeptList;

}

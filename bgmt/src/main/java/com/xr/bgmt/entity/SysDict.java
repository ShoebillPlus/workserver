package com.xr.bgmt.entity;

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
 * 字典表
 * </p>
 *
 * @author yanwei
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_dict")
@ApiModel(value="SysDict对象", description="字典信息")
public class SysDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "code", value = "字典代码")
    private String code;

    @ApiModelProperty(name = "name", value = "字典名称")
    private String name;

    @ApiModelProperty(name = "is_valid", value = "是否有效（0:否；1:是）")
    private Integer isValid;

    @ApiModelProperty(name = "serial", value = "顺序")
    private Integer serial;

    @ApiModelProperty(name = "sys_dict_item_list", value = "字典项列表")
    @TableField(exist = false)
    private List<SysDictItem> sysDictItemList;


}

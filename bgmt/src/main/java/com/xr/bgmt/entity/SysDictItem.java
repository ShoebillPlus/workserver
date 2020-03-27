package com.xr.bgmt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yanwei
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_dict_item")
@ApiModel(value="SysDictItem对象", description="字典项")
public class SysDictItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "dict_id", value = "字典编号")
    private Long dictId;

    @ApiModelProperty(name = "dict_code", value = "字典代码")
    private String dictCode;

    @ApiModelProperty(name = "value", value = "值")
    private String value;

    @ApiModelProperty(name = "label", value = "标签项")
    private String label;

    @ApiModelProperty(name = "serial", value = "顺序")
    private Integer serial;

}

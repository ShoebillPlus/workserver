package com.xr.bgmt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统参数
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_param")
@ApiModel(value="SysParam对象", description="系统参数")
public class SysParam extends Model<SysParam> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "编号")
    private String id;

    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    @ApiModelProperty(name = "config_key", value = "配置键")
    private String configKey;

    @ApiModelProperty(name = "config_value", value = "配置值")
    private String configValue;

    @ApiModelProperty(name = "is_valid", value = "是否有效（0:否；1:是）")
    private Integer isValid;

    @ApiModelProperty(name = "serial", value = "顺序")
    private Integer serial;

    @ApiModelProperty(name = "update_user_id", value = "更新人")
    private String updateUserId;

    @ApiModelProperty(name = "update_time", value = "更新时间")
    private String updateTime;

    @ApiModelProperty(name = "create_user_id", value = "创建人")
    private String createUserId;

    @ApiModelProperty(name = "create_time", value = "创建时间")
    private String createTime;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

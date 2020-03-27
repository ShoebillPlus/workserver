package com.xr.bgmt.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 绩效考核规则
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_ws_kpi_rule")
@ApiModel(value="WsKpiRule对象", description="绩效考核规则")
public class WsKpiRule extends Model<WsKpiRule> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "编号")
    private String id;

    @ApiModelProperty(name = "name", value = "规则名称")
    private String name;

    @ApiModelProperty(name = "total_score", value = "总分")
    private Double totalScore;

    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;

    @ApiModelProperty(name = "is_valid", value = "是否有效（0:否；1:是）")
    private Integer isValid;

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

    @TableField(select = false)
    private List<WsKpiCriterion> wsKpiCriterionList;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

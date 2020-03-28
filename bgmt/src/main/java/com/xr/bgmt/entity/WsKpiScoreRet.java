package com.xr.bgmt.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 得分结果
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_ws_kpi_score_ret")
@ApiModel(value="WsKpiScoreRet对象", description="得分结果")
public class WsKpiScoreRet extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "assessor_id", value = "考核人编号")
    private String assessorId;

    @ApiModelProperty(name = "assessed_id", value = "被考核人编号")
    private String assessedId;

    @ApiModelProperty(name = "rule_id", value = "规则编号")
    private String ruleId;

    @ApiModelProperty(name = "total_score", value = "得分")
    private Double totalScore;

    @ApiModelProperty(name = "opinion", value = "意见")
    private String opinion;

    @ApiModelProperty(name = "month", value = "年月")
    private String month;

    @ApiModelProperty(name = "day", value = "年月日")
    private String day;

    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;

    @ApiModelProperty(name = "is_valid", value = "是否有效（0:否；1:是）")
    @TableField(value = "is_valid", fill = FieldFill.INSERT)
    private Integer isValid;

}

package com.xr.bgmt.entity.form;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *  考评细则
 * </p>
 *
 * @author yanwei
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WxKpiRuleForm对象", description = "考评细则")
public class WxKpiRuleForm extends Model<WxKpiRuleForm> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "rule_id", value = "细则编号")
    private String ruleId;

    @ApiModelProperty(name = "rule_name", value = "细则名称")
    private String ruleName;

    @ApiModelProperty(name = "rule_toltal_score", value = "细则总分")
    private Double ruleToltalScore;

    @ApiModelProperty(name = "criterion_id", value = "指标编号")
    private String criterionId;

    @ApiModelProperty(name = "criterion_name", value = "指标项")
    private String criterionName;

    @ApiModelProperty(name = "criterion_toltal_score", value = "指标总分")
    private Double criterionToltalScore;

    @ApiModelProperty(name = "level_id", value = "等级编号")
    private String levelId;

    @ApiModelProperty(name = "level", value = "等级")
    private String level;

    @ApiModelProperty(name = "content", value = "描述")
    private String content;

    @ApiModelProperty(name = "score", value = "选项分")
    private Double score;

}

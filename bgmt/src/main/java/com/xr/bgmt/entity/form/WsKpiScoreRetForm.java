package com.xr.bgmt.entity.form;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class WsKpiScoreRetForm extends Model<WsKpiScoreRetForm> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "id", value = "编号")
    private String id;

    @ApiModelProperty(name = "assessor_id", value = "考核人编号")
    private String assessorId;

    @ApiModelProperty(name = "assessor_name", value = "考核人姓名")
    private String assessorName;

    @ApiModelProperty(name = "assessed_id", value = "被考核人编号")
    private String assessedId;

    @ApiModelProperty(name = "assessed_name", value = "被考核人姓名")
    private String assessedName;

    @ApiModelProperty(name = "rule_id", value = "规则编号")
    private String ruleId;

    @ApiModelProperty(name = "rule_id", value = "规则名称")
    private String ruleName;

    @ApiModelProperty(name = "total_score", value = "得分")
    private Double totalScore;

    @ApiModelProperty(name = "ret", value = "结果")
    private String ret;

    @ApiModelProperty(name = "opinion", value = "意见")
    private String opinion;

    @ApiModelProperty(name = "month", value = "年月")
    private String month;

    @ApiModelProperty(name = "day", value = "年月日")
    private String day;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

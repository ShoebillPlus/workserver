package com.xr.bgmt.entity.form;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台考评结果详细
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmtKpiRet对象", description = "后台考评结果详细")
public class BgmtKpiRet extends Model<BgmtKpiRet> {

    @ApiModelProperty(name = "assessed_id", value = "被考核人编号")
    private String assessedId;

    @ApiModelProperty(name = "assessed_name", value = "被考核人姓名")
    private String assessedName;

    @ApiModelProperty(name = "dept_id", value = "部门编号")
    private String deptId;

    @ApiModelProperty(name = "deptName", value = "部门名称")
    private String deptName;

    @ApiModelProperty(name = "criterionId", value = "规则编号")
    private String ruleId;

    @ApiModelProperty(name = "ruleName", value = "规则名称")
    private String ruleName;

    @ApiModelProperty(name = "noAssessNum", value = "未考评人数")
    private int noAssessNum;

    @ApiModelProperty(name = "assessNum", value = "已考评人数")
    private int assessNum;

    @ApiModelProperty(name = "avgScore", value = "平均得分")
    private String avgScore;

}

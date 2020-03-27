package com.xr.bgmt.entity.form;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 微信考评结果
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WxKpiRet对象", description = "考评结果")
public class WxKpiRet extends Model<WxKpiRet> {

    @ApiModelProperty(name = "assessorId", value = "考评人")
    private String assessorId;

    @ApiModelProperty(name = "assessedId", value = "被考评人")
    private String assessedId;

    @ApiModelProperty(name = "rule_id", value = "规则编号")
    private String ruleId;

    @ApiModelProperty(name = "month", value = "年月")
    private String month;

    @ApiModelProperty(name = "opinion", value = "意见")
    private String opinion;

    @ApiModelProperty(name = "wxKpiRetDetailList", value = "考评结果详细")
    private List<WxKpiRetDetail> wxKpiRetDetailList;

}

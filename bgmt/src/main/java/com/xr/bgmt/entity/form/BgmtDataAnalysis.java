package com.xr.bgmt.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BgmtDataAnalysis对象", description = "后台数据分析")
public class BgmtDataAnalysis {
    @ApiModelProperty(name = "monthLoginNum", value = "本月小程序登录次数")
    private int monthLoginNum;

    @ApiModelProperty(name = "totalLoginNum", value = "历史小程序登录次数")
    private int totalLoginNum;

    @ApiModelProperty(name = "totalRegisterNum", value = "历史小程序注册次数")
    private int totalRegisterNum;

    @ApiModelProperty(name = "totalKpiNum", value = "本月评分次数")
    private int monthKpiNum;

    @ApiModelProperty(name = "totalKpiNum", value = "历史评分次数")
    private int totalKpiNum;

}

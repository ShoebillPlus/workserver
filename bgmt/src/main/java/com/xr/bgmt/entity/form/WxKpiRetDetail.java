package com.xr.bgmt.entity.form;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 微信考评结果详细
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WxKpiRetDetail对象", description = "考评结果详细")
public class WxKpiRetDetail extends Model<WxKpiRetDetail> {

    @ApiModelProperty(name = "criterionId", value = "指标项编号")
    private String criterionId;

    @ApiModelProperty(name = "levelId", value = "等级编号")
    private String levelId;

    @ApiModelProperty(name = "score", value = "得分")
    private Double score;

}

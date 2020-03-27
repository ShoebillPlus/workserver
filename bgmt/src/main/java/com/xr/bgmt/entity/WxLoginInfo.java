package com.xr.bgmt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * <p>
 * 系统参数
 * </p>
 *
 * @author yanwei
 * @since 2020-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_param")
@ApiModel(value="WxLoginInfo对象", description="微信登录信息")
public class WxLoginInfo extends Model<WxLoginInfo> {

    @ApiModelProperty(name = "code", value = "代码（err、success、noBind）")
    private String code;

    @ApiModelProperty(name = "message", value = "信息")
    private String message;

    @ApiModelProperty(name = "openid", value = "openid")
    private String openid;

    @ApiModelProperty(name = "sessionKey", value = "sessionKey")
    private String sessionKey;

    @ApiModelProperty(name = "userId", value = "用户编号")
    private Long userId;

    @ApiModelProperty(name = "oAuth2AccessToken", value = "Token信息")
    private OAuth2AccessToken oAuth2AccessToken;


}

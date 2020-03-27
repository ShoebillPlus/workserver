package com.xr.bgmt.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@Data
public class MyOAuth2AccessToken {

    @ApiModelProperty(name = "userId", value = "用户编号")
    private Long userId;

    @ApiModelProperty(name = "oAuth2AccessToken", value = "Token信息")
    private OAuth2AccessToken oAuth2AccessToken;
}

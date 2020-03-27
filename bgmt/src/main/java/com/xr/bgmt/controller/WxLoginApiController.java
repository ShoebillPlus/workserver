package com.xr.bgmt.controller;

import com.alibaba.fastjson.JSONObject;
import com.xr.bgmt.entity.MyOAuth2AccessToken;
import com.xr.bgmt.entity.WxLoginInfo;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.WxLoginApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信接口 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-16
 */
@RestController
 @Api(tags = "wx-----------微信接口")
@RequestMapping("/")
@CrossOrigin
public class WxLoginApiController {

    private WxLoginApiService wxLoginApiService;

    @Autowired
    public void setWxLoginApiService(WxLoginApiService wxLoginApiService) {
       this.wxLoginApiService = wxLoginApiService;
    }

    @ApiOperation(value = "获取openid、sessionKey、userInfo、tokenInfo等信息", notes = "获取openid等信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", required = true, paramType = "query", value = "登录凭证")
    })
    @PostMapping("getOpenid")
    public ResponseEntity<WxLoginInfo> getOpenid(@Param("code")String code) throws ApiException {
        return ResponseEntity.ok(wxLoginApiService.getOpenid(code));
    }

    @ApiOperation(value = "小程序首次绑定", notes = "小程序首次绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", required = true, paramType = "query", value = "openid"),
            @ApiImplicitParam(name = "phone", required = true, paramType = "query", value = "手机号")
    })
    @PostMapping("wxBind")
    public ResponseEntity<MyOAuth2AccessToken> wxBind(@Param("openid")String openid,
                                                      @Param("phone")String phone) throws ApiException {
        return new ResponseEntity<>(wxLoginApiService.wxBind(openid, phone), HttpStatus.OK);
    }

    @ApiOperation(value = "解密手机号", notes = "解密手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sessionKey", required = true, paramType = "query", value = "会话标识"),
            @ApiImplicitParam(name = "iv", required = true, paramType = "query", value = "密匙"),
            @ApiImplicitParam(name = "encryptData", required = true, paramType = "query", value = "加密数据")
    })
    @PostMapping("getPhone")
    public ResponseEntity<JSONObject> getPhone(@Param("sessionKey")String sessionKey, @Param("iv")String iv, @Param("encryptData")String encryptData) throws ApiException {
        return ResponseEntity.ok(wxLoginApiService.getPhone(sessionKey,iv,encryptData));
    }

}

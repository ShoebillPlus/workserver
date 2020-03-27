package com.xr.bgmt.service;

import com.alibaba.fastjson.JSONObject;
import com.xr.bgmt.entity.MyOAuth2AccessToken;
import com.xr.bgmt.entity.WxLoginInfo;
import com.xr.bgmt.exception.ApiException;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 * 登录 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
public interface WxLoginApiService {

      //密码模式临时获取token
      public WxLoginInfo getOpenid(String code) throws ApiException;

      //微信绑定获取token
      public MyOAuth2AccessToken wxBind(String openid, String phone) throws ApiException;

      //密码模式临时获取token
      public JSONObject getPhone(String sessionKey,String iv,String encryptData) throws ApiException;
}

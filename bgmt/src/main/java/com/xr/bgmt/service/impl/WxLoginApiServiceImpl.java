package com.xr.bgmt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xr.bgmt.entity.*;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.SysParamService;
import com.xr.bgmt.service.SysUserService;
import com.xr.bgmt.service.WsWxLoginLogService;
import com.xr.bgmt.service.WxLoginApiService;
import com.xr.bgmt.utils.HcUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WxLoginApiServiceImpl implements WxLoginApiService {

    private static final Logger logger = LoggerFactory.getLogger(WxLoginApiServiceImpl.class);

    @Value("${wx-config.login-url}")
    private String loginUrl;
    @Value("${wx-config.appid}")
    private String appid;
    @Value("${wx-config.secret}")
    private String secret;
    @Value("${user-auth-server-url}")
    private String authServerUrl;
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientScret;

    private SysUserService sysUserService;

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    private SysParamService sysParamService;

    @Autowired
    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    private WsWxLoginLogService wsWxLoginLogService;

    @Autowired
    public void setWsWxLoginLogService(WsWxLoginLogService wsWxLoginLogService) {
        this.wsWxLoginLogService = wsWxLoginLogService;
    }

    //获取token
    @Override
    public WxLoginInfo getOpenid(String code) throws ApiException {
        WxLoginInfo wxLoginInfo = new WxLoginInfo();
        try {
            RestTemplate restTemplate = new RestTemplate();
            String getOpenidUrl = loginUrl + "?grant_type=authorization_code&appid=" + appid + "&secret=" + secret
                    + "&js_code=" + code;
            ResponseEntity<String> resEntity = restTemplate.getForEntity(getOpenidUrl, String.class);
            System.out.println(resEntity.getBody());
            JSONObject jsonObject1 = JSONObject.parseObject(resEntity.getBody());
            System.out.println(jsonObject1.get("errcode"));
            if (jsonObject1.getString("errcode") == null) {
                wxLoginInfo.setCode("nobind");
                wxLoginInfo.setMessage("获取openid成功");
                wxLoginInfo.setOpenid(jsonObject1.getString("openid"));
                wxLoginInfo.setSessionKey(jsonObject1.getString("session_key"));
                SysUser sysUser = sysUserService.findByOpenid(jsonObject1.getString("openid"));
                if (sysUser != null) {
                    wxLoginInfo.setCode("success");
                    wxLoginInfo.setMessage("获取人员信息成功");
                    wxLoginInfo.setUserId(sysUser.getId());
                    //获取初始密码
                    /*SysParam sysParam = sysParamService.findByKey("resetPassword");
                    String password = sysParam == null ? "111111" : sysParam.getConfigValue();*/

                    RestTemplate restTemplate1 = new RestTemplate();
                    /*String requestTokenUrl = authServerUrl + "/oauth/token?grant_type=password&client_id=" + clientId + "&client_secret=" + clientScret
                            + "&password=" + password + "&username=" + sysUser.getLoginAccount();*/
                    String requestTokenUrl = authServerUrl + "/oauth/token?grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientScret;

                    ResponseEntity<OAuth2AccessToken> resTokenEntity = restTemplate1.postForEntity(requestTokenUrl, null, OAuth2AccessToken.class);

                    if (resEntity.getStatusCode().value() != 200) {
                        throw new ApiException("授权失败，请重新登录", HttpStatus.BAD_REQUEST);
                    } else {
                        // 注册日志
                        WsWxLoginLog wsWxLoginLog = new WsWxLoginLog();
                        wsWxLoginLog.setOpenid(jsonObject1.getString("openid"));
                        wsWxLoginLog.setType(1);
                        wsWxLoginLog.setUserId(sysUser.getId());
                        wsWxLoginLogService.add(wsWxLoginLog);

                        wxLoginInfo.setMessage("获取人员信息及Token成功");
                        wxLoginInfo.setOAuth2AccessToken(resTokenEntity.getBody());
                    }
                }
            } else {
                wxLoginInfo.setCode("err");
                wxLoginInfo.setMessage(jsonObject1.getString("errmsg"));
            }
            System.out.println(jsonObject1.get("openid"));
        } catch (Exception e) {
            wxLoginInfo.setCode("err");
            wxLoginInfo.setMessage("获取登录信息失败");
        } finally {
            return wxLoginInfo;
        }
    }

    //获取token
    @Override
    public MyOAuth2AccessToken wxBind(String openid, String phone) throws ApiException {

        if (StringUtils.isBlank(phone)) {
            throw new ApiException("绑定手机号为空", HttpStatus.BAD_REQUEST);
        } else {
            //获取用户名
            SysUser sysUser = sysUserService.findByPhone(phone);
            if (sysUser == null) {
                throw new ApiException("你无权登录，请联系管理员", HttpStatus.BAD_REQUEST);
            } else {
                sysUser.setOpenid(openid);
                sysUserService.refresh(sysUser);
                // 注册日志
                WsWxLoginLog wsWxLoginLog = new WsWxLoginLog();
                wsWxLoginLog.setOpenid(openid);
                wsWxLoginLog.setPhone(phone);
                wsWxLoginLog.setType(0);
                wsWxLoginLog.setUserId(sysUser.getId());
                wsWxLoginLogService.add(wsWxLoginLog);
            }
            //获取初始密码
            /*SysParam sysParam = sysParamService.findByKey("resetPassword");
            String password = sysParam == null ? "111111" : sysParam.getConfigValue();*/

            RestTemplate restTemplate = new RestTemplate();
            /*String requestTokenUrl = authServerUrl + "/oauth/token?grant_type=password&client_id=" + clientId + "&client_secret=" + clientScret
                    + "&password=" + password + "&username=" + sysUser.getLoginAccount();*/
            String requestTokenUrl = authServerUrl + "/oauth/token?grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientScret;

            ResponseEntity<OAuth2AccessToken> resEntity = restTemplate.postForEntity(requestTokenUrl, null, OAuth2AccessToken.class);
            if (resEntity.getStatusCode().value() != 200) {
                throw new ApiException("授权失败，请重新登录", HttpStatus.BAD_REQUEST);
            }
            MyOAuth2AccessToken myOAuth2AccessToken = new MyOAuth2AccessToken();
            myOAuth2AccessToken.setUserId(sysUser.getId());
            myOAuth2AccessToken.setOAuth2AccessToken(resEntity.getBody());

            return myOAuth2AccessToken;
        }
    }
    //获取token
    @Override
    public JSONObject getPhone(String sessionKey,String iv,String encryptData) throws ApiException {
        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.parseObject(HcUtil.decryptData(encryptData,sessionKey,iv));
        }catch (Exception e) {
            throw new ApiException("获取手机号失败", HttpStatus.BAD_REQUEST);
        }
        return jsonObject;
    }


}

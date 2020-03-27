package com.xr.bgmt.service.impl;

import com.xr.bgmt.config.MyRedisTokenStore;
import com.xr.bgmt.entity.MyOAuth2AccessToken;
import com.xr.bgmt.entity.SysParam;
import com.xr.bgmt.entity.SysUser;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.LoginService;
import com.xr.bgmt.service.SysParamService;
import com.xr.bgmt.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Value("${user-auth-server-url}")
    private String authServerUrl;
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientScret;
    @Autowired
    private MyRedisTokenStore myRedisTokenStore;

    private SysParamService sysParamService;

    @Autowired
    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    private SysUserService sysUserService;

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    //获取token
    @Override
    public ResponseEntity<OAuth2AccessToken> loginIn(String userName, String password) throws ApiException {
        RestTemplate restTemplate = new RestTemplate();
        String requestTokenUrl = authServerUrl+"/oauth/token?grant_type=password&client_id="+clientId+"&client_secret="+clientScret
                +"&password="+password+"&username="+userName;
        ResponseEntity<OAuth2AccessToken> resEntity =  restTemplate.postForEntity(requestTokenUrl, null, OAuth2AccessToken.class);
        return resEntity;
    }

    @Override
    public Map<String, String> loginout(String token) throws ApiException {
        Map<String, String> result = new HashMap<>();
        try {
            myRedisTokenStore.removeAccessToken(token);
            result.put("code", "100");
            result.put("msg", "注销成功");
        }catch (Exception e){
            result.put("code", "-1");
            result.put("msg", "注销失败");
        }finally {
            return result;
        }
    }
    @Override
    public ResponseEntity<OAuth2AccessToken> refreshToken(String access_token) throws ApiException{
        RestTemplate restTemplate = new RestTemplate();
        String requestTokenUrl = authServerUrl+"/oauth/token?grant_type=refresh_token&client_id="+clientId+"&scop=all&client_secret="+clientScret+"&refresh_token="+access_token;
        ResponseEntity<OAuth2AccessToken> resEntity = restTemplate.postForEntity(requestTokenUrl, null, OAuth2AccessToken.class);
        return resEntity;
    }
}

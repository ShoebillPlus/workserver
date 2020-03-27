package com.xr.bgmt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xr.bgmt.entity.MyOAuth2AccessToken;
import com.xr.bgmt.entity.SysParam;
import com.xr.bgmt.exception.ApiException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Map;

/**
 * <p>
 * 登录 服务类
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
public interface LoginService {

      //密码模式临时获取token
      public ResponseEntity<OAuth2AccessToken> loginIn(String userName, String password) throws ApiException;

      //退出
      public Map<String,String> loginout(String token) throws ApiException ;

      //刷新token
      public ResponseEntity<OAuth2AccessToken> refreshToken(String token) throws ApiException;
}

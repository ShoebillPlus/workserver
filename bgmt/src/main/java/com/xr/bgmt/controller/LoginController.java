package com.xr.bgmt.controller;


import com.xr.bgmt.entity.MyOAuth2AccessToken;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * <p>
 * 登录 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@RestController
 @Api(tags = "登录退出")
@RequestMapping("/")
@CrossOrigin
public class LoginController {

    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
       this.loginService = loginService;
    }

    @ApiOperation(value = "用户登录", notes = "用户登录(获取token)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", required = true, paramType = "query", value = "账号"),
            @ApiImplicitParam(name = "password", required = true, paramType = "query", value = "密码")
    })
    @PostMapping("login")
    public ResponseEntity<OAuth2AccessToken> login(@Param("loginName")String loginName, @Param("password")String password) throws ApiException {
        return loginService.loginIn(loginName, password);
    }

    @ApiOperation(value = "注销", notes = "用户退出")
    @PostMapping("loginout")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", required = true, paramType = "query", value = "token")
    })
    public ResponseEntity<Map> loginout(@Param("token") String token) throws ApiException{
        return ResponseEntity.ok(loginService.loginout(token));
    }

    @ApiOperation(value = "刷新token", notes = "刷新token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", required = true, paramType = "query", value = "token")
    })
    @PostMapping("refreshToken")
    public ResponseEntity<OAuth2AccessToken> refreshToken(@Param("token") String token) throws ApiException{
        return loginService.refreshToken(token);
    }

}

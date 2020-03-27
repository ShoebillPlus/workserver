package com.xr.bgmt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xr.bgmt.entity.SysUser;
import com.xr.bgmt.entity.form.SysUserForm;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags={"ht----------------人员信息"})
@RequestMapping({"/sysUser"})
@CrossOrigin
public class SysUserController
{
    private SysUserService sysUserService;

    @Autowired
    public void setSysUserService(SysUserService sysUserService)
    {
        this.sysUserService = sysUserService;
    }

    @ApiOperation(value="分页查询人员信息列表", notes="分页查询人员信息列表")
    @ApiImplicitParams({@io.swagger.annotations.ApiImplicitParam(name="page", paramType="query", required=true, value="当前页"), @io.swagger.annotations.ApiImplicitParam(name="size", paramType="query", required=true, value="每页数")})
    @GetMapping({"/page"})
    public ResponseEntity<IPage<SysUserForm>> findPage(@PageableDefault Pageable pageable)
            throws ApiException
    {
        IPage page = this.sysUserService.findPage(1, pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }
    @ApiOperation(value="新增人员信息", notes="新增人员信息")
    @PostMapping({"/add"})
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody SysUser sysUser) throws ApiException {
        this.sysUserService.add(sysUser);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="删除人员信息", notes="删除人员信息")
    @ApiImplicitParams({@io.swagger.annotations.ApiImplicitParam(name="id", required=true, paramType="query", value="编号")})
    @DeleteMapping({"/del"})
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
        this.sysUserService.delete(id);
        return ResponseEntity.ok().build();
    }
    @ApiOperation(value="更新人员信息", notes="更新人员信息")
    @PostMapping({"/update"})
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody SysUser sysUser) throws ApiException {
        this.sysUserService.refresh(sysUser);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="根据账号查询用户", notes="根据账号查询用户")
    @ApiImplicitParams({@ApiImplicitParam(name="account", paramType="query", required=true, value="账号")})
    @GetMapping({"/findByAccount"})
    public ResponseEntity<SysUser> findByAccount(@Param("account") String account) throws ApiException {
        SysUser sysUser = this.sysUserService.findByAccount(account);
        return new ResponseEntity(sysUser, HttpStatus.OK);
    }

    @ApiOperation(value="根据openid查询用户", notes="根据openid查询用户")
    @ApiImplicitParams({@io.swagger.annotations.ApiImplicitParam(name="openid", paramType="query", required=true, value="openid")})
    @GetMapping({"/findByOpenid"})
    public ResponseEntity<SysUser> findByOpenid(@Param("openid") String openid) throws ApiException {
        SysUser sysUser = this.sysUserService.findByOpenid(openid);
        return new ResponseEntity(sysUser, HttpStatus.OK);
    }
}
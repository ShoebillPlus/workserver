package com.xr.bgmt.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.SysRoleService;
import com.xr.bgmt.entity.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.ResponseEntity;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * <p>
 *  角色信息 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@RestController
 @Api(tags = "ht----------------角色信息")
@RequestMapping("/sysRole")
@CrossOrigin
public class SysRoleController {

    private SysRoleService sysRoleService;

    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
       this.sysRoleService = sysRoleService;
    }

    @ApiOperation(value = "分页查询列表", notes = "分页查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<SysRole>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<SysRole> page = sysRoleService.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody SysRole sysRole) throws ApiException {
        sysRoleService.add(sysRole);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         sysRoleService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody SysRole sysRole) throws ApiException {
        sysRoleService.refresh(sysRole);
        return ResponseEntity.ok().build();
    }

}

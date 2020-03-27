package com.xr.bgmt.controller;


import com.xr.bgmt.entity.SysUserDeptsRoles;
import com.xr.bgmt.entity.form.UserDeptsRoles;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.SysUserDeptsRolesService;
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

import java.util.List;

/**
 * <p>
 * 用户部门 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@RestController
 @Api(tags = "ht----------------用户部门角色")
@RequestMapping("/sysUserDeptsRoles")
@CrossOrigin
public class SysUserDeptsRolesController {

    private SysUserDeptsRolesService sysUserDeptsRolesService;

    @Autowired
    public void setSysUserDeptsRolesService(SysUserDeptsRolesService sysUserDeptsRolesService) {
       this.sysUserDeptsRolesService = sysUserDeptsRolesService;
    }

    @ApiOperation(value = "分页查询用户部门列表", notes = "分页查询用户部门列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<SysUserDeptsRoles>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<SysUserDeptsRoles> page = sysUserDeptsRolesService.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增用户部门角色(多)", notes = "新增用户部门角色(多)")
    @PostMapping("/addList")
    public ResponseEntity<HttpStatus> addList(@Validated @RequestBody UserDeptsRoles userDeptsRoles) throws ApiException {
        sysUserDeptsRolesService.addList(userDeptsRoles);
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "新增用户部门角色", notes = "新增用户部门角色")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody SysUserDeptsRoles sysUserDeptsRoles) throws ApiException {
        sysUserDeptsRolesService.add(sysUserDeptsRoles);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除用户部门", notes = "删除用户部门")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         sysUserDeptsRolesService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新用户部门", notes = "更新用户部门")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody SysUserDeptsRoles sysUserDeptsRoles) throws ApiException {
        sysUserDeptsRolesService.refresh(sysUserDeptsRoles);
        return ResponseEntity.ok().build();
    }

}

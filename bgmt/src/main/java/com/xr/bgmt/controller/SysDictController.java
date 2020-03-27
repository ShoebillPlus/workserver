package com.xr.bgmt.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.SysDictService;
import com.xr.bgmt.entity.SysDict;
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
 * 字典表 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-23
 */
@RestController
 @Api(tags = "ht----------------字典表")
@RequestMapping("/sysDict")
@CrossOrigin
public class SysDictController {

    private SysDictService sysDictService;

    @Autowired
    public void setSysDictService(SysDictService sysDictService) {
       this.sysDictService = sysDictService;
    }

    @ApiOperation(value = "分页查询字典表列表", notes = "分页查询字典表列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<SysDict>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<SysDict> page = sysDictService.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增字典表", notes = "新增字典表")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody SysDict sysDict) throws ApiException {
        sysDictService.add(sysDict);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除字典表", notes = "删除字典表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         sysDictService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新字典表", notes = "更新字典表")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody SysDict sysDict) throws ApiException {
        sysDictService.refresh(sysDict);
        return ResponseEntity.ok().build();
    }
}

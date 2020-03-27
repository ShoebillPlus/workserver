package com.xr.bgmt.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.SysDeptService;
import com.xr.bgmt.entity.SysDept;
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
 * 机构部门 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@RestController
 @Api(tags = "ht----------------机构部门")
@RequestMapping("/sysDept")
@CrossOrigin
public class SysDeptController {

    private SysDeptService sysDeptService;

    @Autowired
    public void setSysDeptService(SysDeptService sysDeptService) {
       this.sysDeptService = sysDeptService;
    }

    @ApiOperation(value = "查询机构部门树型", notes = "查询机构部门树型")
    @GetMapping("/tree")
    public ResponseEntity<JSONArray> findTree() throws ApiException {
        JSONArray jsonArray = sysDeptService.findTree();
        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    @ApiOperation(value = "分页查询机构部门列表", notes = "分页查询机构部门列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<SysDept>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<SysDept> page = sysDeptService.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增机构部门", notes = "新增机构部门")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody SysDept sysDept) throws ApiException {
        sysDeptService.add(sysDept);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除机构部门", notes = "删除机构部门")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         sysDeptService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新机构部门", notes = "更新机构部门")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody SysDept sysDept) throws ApiException {
        sysDeptService.refresh(sysDept);
        return ResponseEntity.ok().build();
    }

}

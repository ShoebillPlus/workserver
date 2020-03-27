package com.xr.bgmt.controller;


import com.xr.bgmt.exception.ApiException;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.service.SysParamService;
import com.xr.bgmt.entity.SysParam;
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
 * 系统参数 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@RestController
 @Api(tags = "系统参数")
@RequestMapping("/sysParam")
@CrossOrigin
public class SysParamController {

    private SysParamService sysParamService;

    @Autowired
    public void setSysParamService(SysParamService sysParamService) {
       this.sysParamService = sysParamService;
    }

    @ApiOperation(value = "分页查询系统参数列表", notes = "分页查询系统参数列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<SysParam>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<SysParam> page = sysParamService.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增系统参数", notes = "新增系统参数")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody SysParam sysParam) throws ApiException {
        sysParamService.add(sysParam);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除系统参数", notes = "删除系统参数")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         sysParamService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新系统参数", notes = "更新系统参数")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody SysParam sysParam) throws ApiException {
        sysParamService.refresh(sysParam);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "根据配置键查询系统参数", notes = "根据配置键查询系统参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "configKey", paramType = "query", required = true, value = "配置键")
    })
    @GetMapping("/findByKey")
    public ResponseEntity<SysParam> findByKey(@Param("configKey") String configKey) throws ApiException {
        SysParam sysParam = sysParamService.findByKey(configKey);
        return new ResponseEntity<>(sysParam, HttpStatus.OK);
    }

}

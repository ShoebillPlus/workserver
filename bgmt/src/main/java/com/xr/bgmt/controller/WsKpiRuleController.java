package com.xr.bgmt.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.WsKpiRuleService;
import com.xr.bgmt.entity.WsKpiRule;
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
 * 绩效考核规则 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@RestController
 @Api(tags = "绩效考核规则")
@RequestMapping("/wsKpiRule")
@CrossOrigin
public class WsKpiRuleController {

    private WsKpiRuleService wsKpiRuleService;

    @Autowired
    public void setWsKpiRuleService(WsKpiRuleService wsKpiRuleService) {
       this.wsKpiRuleService = wsKpiRuleService;
    }

    @ApiOperation(value = "分页查询绩效考核规则列表", notes = "分页查询绩效考核规则列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<WsKpiRule>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<WsKpiRule> page = wsKpiRuleService.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增绩效考核规则", notes = "新增绩效考核规则")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody WsKpiRule wsKpiRule) throws ApiException {
        wsKpiRuleService.add(wsKpiRule);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除绩效考核规则", notes = "删除绩效考核规则")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         wsKpiRuleService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新绩效考核规则", notes = "更新绩效考核规则")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody WsKpiRule wsKpiRule) throws ApiException {
        wsKpiRuleService.refresh(wsKpiRule);
        return ResponseEntity.ok().build();
    }

}

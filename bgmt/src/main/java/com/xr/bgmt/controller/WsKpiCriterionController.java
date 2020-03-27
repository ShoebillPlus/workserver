package com.xr.bgmt.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.WsKpiCriterionService;
import com.xr.bgmt.entity.WsKpiCriterion;
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
 * 绩效指标 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@RestController
 @Api(tags = "绩效指标")
@RequestMapping("/wsKpiCriterion")
@CrossOrigin
public class WsKpiCriterionController {

    private WsKpiCriterionService wsKpiCriterionService;

    @Autowired
    public void setWsKpiCriterionService(WsKpiCriterionService wsKpiCriterionService) {
       this.wsKpiCriterionService = wsKpiCriterionService;
    }

    @ApiOperation(value = "分页查询绩效指标列表", notes = "分页查询绩效指标列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数"),
        @ApiImplicitParam(name = "ruleId", paramType = "query",value = "规则编号")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<WsKpiCriterion>> findPage(@PageableDefault Pageable pageable,@Param("ruleId") String ruleId) throws ApiException {
        IPage<WsKpiCriterion> page = wsKpiCriterionService.findPage(pageable,ruleId);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增绩效指标", notes = "新增绩效指标")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody WsKpiCriterion wsKpiCriterion) throws ApiException {
        wsKpiCriterionService.add(wsKpiCriterion);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除绩效指标", notes = "删除绩效指标")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         wsKpiCriterionService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新绩效指标", notes = "更新绩效指标")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody WsKpiCriterion wsKpiCriterion) throws ApiException {
        wsKpiCriterionService.refresh(wsKpiCriterion);
        return ResponseEntity.ok().build();
    }

}

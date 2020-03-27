package com.xr.bgmt.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.WsKpiLevelService;
import com.xr.bgmt.entity.WsKpiLevel;
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
 * 考核等级 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@RestController
 @Api(tags = "考核等级")
@RequestMapping("/wsKpiLevel")
@CrossOrigin
public class WsKpiLevelController {

    private WsKpiLevelService wsKpiLevelService;

    @Autowired
    public void setWsKpiLevelService(WsKpiLevelService wsKpiLevelService) {
       this.wsKpiLevelService = wsKpiLevelService;
    }

    @ApiOperation(value = "分页查询考核等级列表", notes = "分页查询考核等级列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数"),
        @ApiImplicitParam(name = "ruleId", paramType = "query",value = "规则编号"),
        @ApiImplicitParam(name = "criterionId", paramType = "query",value = "指标编号")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<WsKpiLevel>> findPage(@PageableDefault Pageable pageable,@Param("ruleId") String ruleId,@Param("criterionId") String criterionId) throws ApiException {
        IPage<WsKpiLevel> page = wsKpiLevelService.findPage(pageable,ruleId,criterionId);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增考核等级", notes = "新增考核等级")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody WsKpiLevel wsKpiLevel) throws ApiException {
        wsKpiLevelService.add(wsKpiLevel);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除考核等级", notes = "删除考核等级")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         wsKpiLevelService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新考核等级", notes = "更新考核等级")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody WsKpiLevel wsKpiLevel) throws ApiException {
        wsKpiLevelService.refresh(wsKpiLevel);
        return ResponseEntity.ok().build();
    }

}

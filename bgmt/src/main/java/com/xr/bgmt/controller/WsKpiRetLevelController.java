package com.xr.bgmt.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.WsKpiRetLevelService;
import com.xr.bgmt.entity.WsKpiRetLevel;
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
 * 得分结果等级 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@RestController
 @Api(tags = "得分结果等级")
@RequestMapping("/wsKpiRetLevel")
@CrossOrigin
public class WsKpiRetLevelController {

    private WsKpiRetLevelService wsKpiRetLevelService;

    @Autowired
    public void setWsKpiRetLevelService(WsKpiRetLevelService wsKpiRetLevelService) {
       this.wsKpiRetLevelService = wsKpiRetLevelService;
    }

    @ApiOperation(value = "分页查询得分结果等级列表", notes = "分页查询得分结果等级列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<WsKpiRetLevel>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<WsKpiRetLevel> page = wsKpiRetLevelService.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增得分结果等级", notes = "新增得分结果等级")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody WsKpiRetLevel wsKpiRetLevel) throws ApiException {
        wsKpiRetLevelService.add(wsKpiRetLevel);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除得分结果等级", notes = "删除得分结果等级")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         wsKpiRetLevelService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新得分结果等级", notes = "更新得分结果等级")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody WsKpiRetLevel wsKpiRetLevel) throws ApiException {
        wsKpiRetLevelService.refresh(wsKpiRetLevel);
        return ResponseEntity.ok().build();
    }

}

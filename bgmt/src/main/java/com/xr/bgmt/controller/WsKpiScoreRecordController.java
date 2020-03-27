package com.xr.bgmt.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.WsKpiScoreRecordService;
import com.xr.bgmt.entity.WsKpiScoreRecord;
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
 * 得分记录 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@RestController
 @Api(tags = "得分记录")
@RequestMapping("/wsKpiScoreRecord")
@CrossOrigin
public class WsKpiScoreRecordController {

    private WsKpiScoreRecordService wsKpiScoreRecordService;

    @Autowired
    public void setWsKpiScoreRecordService(WsKpiScoreRecordService wsKpiScoreRecordService) {
       this.wsKpiScoreRecordService = wsKpiScoreRecordService;
    }

    @ApiOperation(value = "分页查询得分记录列表", notes = "分页查询得分记录列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<WsKpiScoreRecord>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<WsKpiScoreRecord> page = wsKpiScoreRecordService.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增得分记录", notes = "新增得分记录")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody WsKpiScoreRecord wsKpiScoreRecord) throws ApiException {
        wsKpiScoreRecordService.add(wsKpiScoreRecord);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除得分记录", notes = "删除得分记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         wsKpiScoreRecordService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新得分记录", notes = "更新得分记录")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody WsKpiScoreRecord wsKpiScoreRecord) throws ApiException {
        wsKpiScoreRecordService.refresh(wsKpiScoreRecord);
        return ResponseEntity.ok().build();
    }

}

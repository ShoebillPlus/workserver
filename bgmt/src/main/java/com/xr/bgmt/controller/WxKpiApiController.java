package com.xr.bgmt.controller;


import com.xr.bgmt.entity.WsKpiRule;
import com.xr.bgmt.entity.form.WxKpiRet;
import com.xr.bgmt.entity.form.WxKpiRuleForm;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.WxKpiApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微信绩效考核接口 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-16
 */
@RestController
 @Api(tags = "wx-----------微信绩效考核接口")
@RequestMapping("/wxKpi")
@CrossOrigin
public class WxKpiApiController {

    private WxKpiApiService wxKpiApiService;

    @Autowired
    public void setWxKpiApiService(WxKpiApiService wxKpiApiService) {
       this.wxKpiApiService = wxKpiApiService;
    }

    @ApiOperation(value = "获取被考评人信息", notes = "获取被考评人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assessorId", required = true, paramType = "query", value = "考评人编号")
    })
    @GetMapping("getAssesseder")
    public ResponseEntity<Map<String,Object>> getAssesseder(@Param("assessorId")String assessorId) throws ApiException {
        return ResponseEntity.ok(wxKpiApiService.getAssesseder(assessorId));
    }

    @ApiOperation(value = "获取考评细则", notes = "获取考评细则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assessedId", required = true, paramType = "query", value = "被考评人编号")
    })
    @GetMapping("getKpiRule")
    public ResponseEntity<WsKpiRule> getKpiRule(@Param("assessedId")String assessedId) throws ApiException {
        return ResponseEntity.ok(wxKpiApiService.getKpiRule(assessedId));
    }

    @ApiOperation(value = "考评结果上报", notes = "考评结果上报")
    @PostMapping("submitRet")
    public ResponseEntity<HttpStatus> submitRet(@Validated @RequestBody WxKpiRet wxKpiRet) throws ApiException {
        wxKpiApiService.submitRet(wxKpiRet);
        return ResponseEntity.ok().build();
    }

}

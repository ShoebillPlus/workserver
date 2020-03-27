package com.xr.bgmt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xr.bgmt.entity.WsKpiScoreRet;
import com.xr.bgmt.entity.form.BgmtKpiRet;
import com.xr.bgmt.entity.form.WsKpiScoreRetForm;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.BgmtKpiApiService;
import com.xr.bgmt.service.WsKpiScoreRetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 得分结果 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
@RestController
 @Api(tags = "ht----------------后台结果")
@RequestMapping("/bgmtKpi")
@CrossOrigin
public class BgmtKpiApiController {

    private BgmtKpiApiService bgmtKpiApiService;

    @Autowired
    public void setBgmtKpiApiService(BgmtKpiApiService bgmtKpiApiService) {
       this.bgmtKpiApiService = bgmtKpiApiService;
    }

    @ApiOperation(value = "考评详细信息", notes = "考评详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数"),
        @ApiImplicitParam(name = "month", paramType = "query", required = true, value = "年月（YYYY-MM）")
    })
    @GetMapping("/kpiDetail")
    public ResponseEntity<IPage<WsKpiScoreRetForm>> kpiDetail(@Param("month") String month, @PageableDefault Pageable pageable) throws ApiException {
        IPage<WsKpiScoreRetForm> page = bgmtKpiApiService.kpiDetailPage(month,pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "考评结果信息", notes = "考评结果信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
            @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数"),
            @ApiImplicitParam(name = "month", paramType = "query", required = true, value = "年月（YYYY-MM）")
    })
    @GetMapping("/kpiRet")
    public ResponseEntity<IPage<BgmtKpiRet>> kpiRet(@Param("month") String month, @PageableDefault Pageable pageable) throws ApiException {
        IPage<BgmtKpiRet> page = bgmtKpiApiService.kpiRet(month,pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "考评结果信息", notes = "考评结果信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
            @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数"),
            @ApiImplicitParam(name = "month", paramType = "query", required = true, value = "年月（YYYY-MM）")
    })
    @GetMapping("/kpiRetExport")
    public ResponseEntity<HttpStatus> kpiRetExport(@Param("month") String month, @PageableDefault Pageable pageable, HttpServletResponse response) throws ApiException {
        bgmtKpiApiService.kpiRetExport(month,pageable,response);
        return ResponseEntity.ok().build();
    }


}

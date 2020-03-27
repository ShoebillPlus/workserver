package com.xr.bgmt.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xr.bgmt.exception.ApiException;
import com.xr.bgmt.service.SysDictItemService;
import com.xr.bgmt.entity.SysDictItem;
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
import java.util.List;

/**
 * <p>
 *  字典项信息 前端控制器
 * </p>
 *
 * @author yanwei
 * @since 2020-03-23
 */
@RestController
 @Api(tags = "ht----------------字典项信息")
@RequestMapping("/sysDictItem")
@CrossOrigin
public class SysDictItemController {

    private SysDictItemService sysDictItemService;

    @Autowired
    public void setSysDictItemService(SysDictItemService sysDictItemService) {
       this.sysDictItemService = sysDictItemService;
    }

    @ApiOperation(value = "分页查询列表", notes = "分页查询列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<SysDictItem>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<SysDictItem> page = sysDictItemService.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody SysDictItem sysDictItem) throws ApiException {
        sysDictItemService.add(sysDictItem);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         sysDictItemService.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新", notes = "更新")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody SysDictItem sysDictItem) throws ApiException {
        sysDictItemService.refresh(sysDictItem);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="根据字典代码查询字典项", notes="根据字典代码查询字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictCode", paramType = "query", required = true, value = "字典代码")
    })
    @GetMapping({"/findByDictCode"})
    public ResponseEntity<List<SysDictItem>> findByDictCode(@Param("dictCode") String dictCode) throws ApiException {
        List<SysDictItem> sysDictItemList = sysDictItemService.findByDictCode(dictCode);
        return new ResponseEntity(sysDictItemList, HttpStatus.OK);
    }

}

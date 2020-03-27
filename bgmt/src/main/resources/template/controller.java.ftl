package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import ${package.Controller?substring(0,(package.Controller)?length-10)}exception.ApiException;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
</#if>
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
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if swagger2>
 @Api(tags = "${table.comment!}")
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@CrossOrigin
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private ${table.serviceName} ${(table.serviceName)?uncap_first};

    @Autowired
    public void set${table.serviceName}(${table.serviceName} ${(table.serviceName)?uncap_first}) {
       this.${(table.serviceName)?uncap_first} = ${(table.serviceName)?uncap_first};
    }

    @ApiOperation(value = "分页查询${table.comment!}列表", notes = "分页查询${table.comment!}列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", paramType = "query", required = true, value = "当前页"),
        @ApiImplicitParam(name = "size", paramType = "query", required = true, value = "每页数")
    })
    @GetMapping("/page")
    public ResponseEntity<IPage<${entity!}>> findPage(@PageableDefault Pageable pageable) throws ApiException {
        IPage<${entity!}> page = ${(table.serviceName)?uncap_first}.findPage(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ApiOperation(value = "新增${table.comment!}", notes = "新增${table.comment!}")
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@Validated @RequestBody ${entity!} ${entity?uncap_first}) throws ApiException {
        ${(table.serviceName)?uncap_first}.add(${entity?uncap_first});
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "删除${table.comment!}", notes = "删除${table.comment!}")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", required = true, paramType = "query", value = "编号")
    })
    @DeleteMapping("/del")
    public ResponseEntity<HttpStatus> delete(String id) throws ApiException {
         ${(table.serviceName)?uncap_first}.delete(id);
         return ResponseEntity.ok().build();
     }

    @ApiOperation(value = "更新${table.comment!}", notes = "更新${table.comment!}")
    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@Validated @RequestBody ${entity!} ${entity?uncap_first}) throws ApiException {
        ${(table.serviceName)?uncap_first}.refresh(${entity?uncap_first});
        return ResponseEntity.ok().build();
    }

}
</#if>

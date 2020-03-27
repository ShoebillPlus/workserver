package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import ${package.Service?substring(0,(package.Service)?length-7)}exception.ApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Pageable;
/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
      /**
       * 根据条件分页查询${table.comment!}
       *
       * @param pageable 分页对象
       * @return IPage<${entity}>   ${table.comment!}列表（分页）
       */
       IPage<${entity}> findPage(Pageable pageable) throws ApiException;

      /**
       * 增加${table.comment!}
       *
       * @param ${entity?uncap_first} ${table.comment!}
       * @throws ApiException 异常信息
       */
      void add(${entity} ${entity?uncap_first}) throws ApiException;

      /**
      * 删除${table.comment!}
      *
      * @param id 编号
      * @throws ApiException 异常信息
      */
      void delete(String id) throws ApiException;

      /**
      * 更新${table.comment!}
      *
      * @param ${entity?uncap_first} ${table.comment!}
      * @throws ApiException 异常信息
      */
      void refresh(${entity} ${entity?uncap_first}) throws ApiException;

}
</#if>

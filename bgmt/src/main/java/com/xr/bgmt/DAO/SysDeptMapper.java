package com.xr.bgmt.DAO;

import com.xr.bgmt.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 机构部门 Mapper 接口
 * </p>
 *
 * @author yanwei
 * @since 2020-03-11
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 获取考核细则内容
     *
     * @return
     * @throws Exception
     */
    int getMaxLevel() throws Exception;

    List<SysDept> findDeptsNoMe(@Param("userId") Long userId) throws Exception;

}

package com.xr.bgmt.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xr.bgmt.entity.WsKpiRule;
import com.xr.bgmt.entity.form.BgmtKpiRet;
import com.xr.bgmt.entity.form.SysUserForm;
import com.xr.bgmt.entity.form.WsKpiScoreRetForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 后台接口 Mapper 接口
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@Component
public interface BgmtKpiApiMapper extends BaseMapper<WsKpiScoreRetForm> {

    /**
     * 自定义sql分页
     * @param page
     * @param
     * @return
     */
    IPage<WsKpiScoreRetForm> kpiDetailPage(@Param("month") String month,IPage<WsKpiScoreRetForm> page);

    /**
     * 自定义sql分页
     * @param page
     * @param
     * @return
     */
    IPage<BgmtKpiRet> BgmtKpiRet(@Param("type") int type, IPage<BgmtKpiRet> page);


}

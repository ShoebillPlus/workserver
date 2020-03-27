package com.xr.bgmt.DAO;

import com.xr.bgmt.entity.WsKpiRule;
import com.xr.bgmt.entity.form.SysUserForm;
import com.xr.bgmt.entity.form.WsKpiScoreRetForm;
import com.xr.bgmt.entity.form.WxKpiRuleForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 人员信息 Mapper 接口
 * </p>
 *
 * @author yanwei
 * @since 2020-03-09
 */
@Component
public interface WxKpiApiMapper {
    /**
     * 获取需考核人员信息列表
     *
     * @return
     * @throws Exception
     */
    List<SysUserForm> getAssessederList(@Param("assessorId") String assessorId,@Param("isAss") String isAss) throws Exception;

    /**
     * 获取月份考核信息
     *
     * @return
     * @throws Exception
     */
    WsKpiScoreRetForm getAssessederRet(@Param("month") String month,
                                       @Param("assessorId") String assessorId, @Param("assessedId") String assessedId) throws Exception;
    /**
     * 获取考核细则内容
     *
     * @return
     * @throws Exception
     */
    WsKpiRule getKpiRule(@Param("assessedId") String assessedId) throws Exception;
}

package com.xr.bgmt.quartz;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xr.bgmt.entity.SysDept;
import com.xr.bgmt.entity.SysUser;
import com.xr.bgmt.entity.WsKpiDept;
import com.xr.bgmt.entity.WsKpiRelation;
import com.xr.bgmt.service.SysDeptService;
import com.xr.bgmt.service.SysUserService;
import com.xr.bgmt.service.WsKpiDeptService;
import com.xr.bgmt.service.WsKpiRelationService;
import com.xr.bgmt.utils.HcUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleJob2 extends QuartzJobBean {

    private WsKpiDeptService wsKpiDeptService;

    public void setWsKpiDeptService(WsKpiDeptService wsKpiDeptService) {
        this.wsKpiDeptService = wsKpiDeptService;
    }
    private SysUserService sysUserService;

    public void setSysUserService(SysUserService sysUserService)
    {
        this.sysUserService = sysUserService;
    }

    private SysDeptService sysDeptService;

    public void setSysDeptService(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    private WsKpiRelationService wsKpiRelationService;

    public void setWsKpiRelationService(WsKpiRelationService wsKpiRelationService) {
        this.wsKpiRelationService = wsKpiRelationService;
    }
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            System.out.println("------------------任务开始Job2（制作详细评价对应关系）------------------");
            List<SysUser> sysUserList =  sysUserService.list();
            String month = HcUtil.getMonth(-1);
            for(int i = 0;i<sysUserList.size();i++){
                SysUser sysUser = sysUserList.get(i);
                if(sysUser.getIsValid().equals(1)&&sysUser.getType().equals(1)){
                    List<SysUser> assessedUserList = sysUserService.findBySameDept(sysUser.getId(),month);
                    for(int j = 0;j<assessedUserList.size();j++){
                        WsKpiRelation wsKpiRelation = new WsKpiRelation();
                        wsKpiRelation.setAssessorId(sysUser.getId());
                        wsKpiRelation.setAssessedId(assessedUserList.get(j).getId());
                        wsKpiRelation.setMonth(month);
                        // 获取规则和评分比例
                        wsKpiRelation.setRuleId(assessedUserList.get(j).getRuleId());
                        List<SysUser> sysUserList1 =  sysUserService.findByMinister(sysUser.getId());
                        List<SysUser> sysUserList2 =  sysUserService.findByMinister(assessedUserList.get(j).getId());
                        // 总经理评价部长
                        if(sysUser.getId()<=3&&sysUserList2.size()>0){
                            wsKpiRelation.setScaleType(6);
                            wsKpiRelation.setScale(0.3D);
                        }
                        // 其他部门领导评领导
                        if(sysUserList1.size()>0&&sysUserList2.size()>0){
                            wsKpiRelation.setScaleType(5);
                            wsKpiRelation.setScale(0.3D);
                        }
                        // 领导评员工
                        if(sysUserList1.size()>0&&sysUserList2.size()<=0){
                            // 判断是否在同一部门
                            if(sysUserService.checkDept(sysUser.getId(),assessedUserList.get(j).getId())>0){
                                wsKpiRelation.setScaleType(3);
                                wsKpiRelation.setScale(0.5D);
                            }else{
                                wsKpiRelation.setScaleType(4);
                                wsKpiRelation.setScale(0.3D);
                            }
                        }
                        // 同部门员工评员工
                        if(sysUserList1.size()<=0&&sysUserList2.size()<=0){
                            wsKpiRelation.setScaleType(1);
                            wsKpiRelation.setScale(0.2D);
                        }
                        // 同部门员工评领导
                        if(sysUserList1.size()<=0&&sysUserList2.size()>0){
                            wsKpiRelation.setScaleType(2);
                            wsKpiRelation.setScale(0.3D);
                        }
                        wsKpiRelation.setState(assessedUserList.get(j).getIsKpi());
                        UpdateWrapper wrapper = new UpdateWrapper();
                        wrapper.eq("assessor_id", wsKpiRelation.getAssessorId());
                        wrapper.eq("assessed_id", wsKpiRelation.getAssessedId());
                        wrapper.eq("month", wsKpiRelation.getMonth());
                        wsKpiRelationService.saveOrUpdate(wsKpiRelation,wrapper);
                    }
                }
            }
            System.out.println("------------------任务结束Job2------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

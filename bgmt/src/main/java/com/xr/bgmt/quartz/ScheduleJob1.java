package com.xr.bgmt.quartz;

import com.xr.bgmt.entity.SysDept;
import com.xr.bgmt.entity.SysUser;
import com.xr.bgmt.entity.WsKpiDept;
import com.xr.bgmt.service.SysDeptService;
import com.xr.bgmt.service.SysUserService;
import com.xr.bgmt.service.WsKpiDeptService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleJob1 extends QuartzJobBean {

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
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            System.out.println("------------------任务开始Job1（分配领导评价其他部门人员）------------------");
            List<SysUser> sysUserList =  sysUserService.findByMinister(null);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date); // 设置为当前时间
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
            date = calendar.getTime();
            String month = df.format(date);
            for(int i = 0;i<sysUserList.size();i++){
               List<WsKpiDept> wsKpiDeptList =  wsKpiDeptService.findByUserIdAndMonth(sysUserList.get(i).getId(),month);
                if((wsKpiDeptList.size()>2)){
                    for(int j= wsKpiDeptList.size();j<2 ;j--){
                        wsKpiDeptService.delete(wsKpiDeptList.get(j).getId().toString());
                    }
                }
                List<SysDept> sysDeptList = sysDeptService.findDeptsNoMe(sysUserList.get(i).getId());
                List<Integer>  randoms =  new ArrayList<>();
                int random;
               for(int j= wsKpiDeptList.size();j<2 ;j++){
                   random =(int)(Math.random()*sysDeptList.size());
                   while (randoms.size()>0&&randoms.contains(random)){
                       random=(int)(Math.random()*sysDeptList.size());
                   }
                   randoms.add(random);
                   System.out.println(random);
                   WsKpiDept wsKpiDept = new WsKpiDept();
                   wsKpiDept.setDeptId(sysDeptList.get(random).getId());
                   wsKpiDept.setMonth(month);
                   wsKpiDept.setUserId(sysUserList.get(i).getId());
                   wsKpiDeptService.add(wsKpiDept);
               }
            }
            System.out.println("------------------任务结束Job1------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

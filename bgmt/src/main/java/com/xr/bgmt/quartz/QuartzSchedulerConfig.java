package com.xr.bgmt.quartz;


import com.xr.bgmt.entity.WsKpiRelation;
import com.xr.bgmt.service.SysDeptService;
import com.xr.bgmt.service.SysUserService;
import com.xr.bgmt.service.WsKpiDeptService;
import com.xr.bgmt.service.WsKpiRelationService;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@EnableScheduling
public class QuartzSchedulerConfig {

    @Bean(name = "job1DataMap")
    public JobDataMap job1DataMap(@Autowired WsKpiDeptService wsKpiDeptService, @Autowired SysUserService sysUserService, @Autowired SysDeptService sysDeptService){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("wsKpiDeptService", wsKpiDeptService);
        jobDataMap.put("sysUserService", sysUserService);
        jobDataMap.put("sysDeptService", sysDeptService);
        return jobDataMap;
    }

    @Bean(name = "job1Factory")
    public JobDetailFactoryBean jobDetailFactoryBean1(@Qualifier("job1DataMap") JobDataMap job1DataMap) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobDataMap(job1DataMap);
        jobDetailFactoryBean.setJobClass(ScheduleJob1.class);
        jobDetailFactoryBean.setName("job1Factory");
        return jobDetailFactoryBean;
    }

    @Bean(name = "job2DataMap")
    public JobDataMap job2DataMap(@Autowired WsKpiDeptService wsKpiDeptService, @Autowired SysUserService sysUserService, @Autowired SysDeptService sysDeptService,
                                  @Autowired WsKpiRelationService wsKpiRelationService){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("wsKpiDeptService", wsKpiDeptService);
        jobDataMap.put("sysUserService", sysUserService);
        jobDataMap.put("sysDeptService", sysDeptService);
        jobDataMap.put("wsKpiRelationService", wsKpiRelationService);
        return jobDataMap;
    }

    @Bean(name = "job2Factory")
    public JobDetailFactoryBean jobDetailFactoryBean2(@Qualifier("job2DataMap") JobDataMap job2DataMap) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobDataMap(job2DataMap);
        jobDetailFactoryBean.setJobClass(ScheduleJob2.class);
        jobDetailFactoryBean.setName("job2Factory");
        return jobDetailFactoryBean;
    }

    @Bean(name = "cronTrigger1")
    public CronTriggerFactoryBean cronTriggerFactoryBean1(@Qualifier("job1Factory") JobDetailFactoryBean job1DetailFactoryBean) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(job1DetailFactoryBean.getObject());
        cronTriggerFactoryBean.setBeanName("cronTrigger1");
        cronTriggerFactoryBean.setStartDelay(1000);
        //cronTriggerFactoryBean.setCronExpression("10/20 * * * * ? ");
        //cronTriggerFactoryBean.setCronExpression("0 30 23 L * ?");
        cronTriggerFactoryBean.setCronExpression("0 0 0 1 * ?");
        return cronTriggerFactoryBean;
    }
    @Bean(name = "quartzScheduler")
    public SchedulerFactoryBean schedulerFactoryBean1(@Qualifier("cronTrigger1") CronTrigger simpleTriggerFactoryBean, @Qualifier("cronTrigger2") CronTrigger cronTriggerFactoryBean) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setBeanName("quartzScheduler1");
        schedulerFactoryBean.setTriggers(simpleTriggerFactoryBean,cronTriggerFactoryBean);
        return schedulerFactoryBean;
    }

    @Bean(name = "cronTrigger2")
    public CronTriggerFactoryBean cronTriggerFactoryBean2(@Qualifier("job2Factory") JobDetailFactoryBean job2DetailFactoryBean) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(job2DetailFactoryBean.getObject());
        cronTriggerFactoryBean.setBeanName("cronTrigger2");
        cronTriggerFactoryBean.setStartDelay(1000);
        cronTriggerFactoryBean.setCronExpression("0 47 * * * ? ");
        //cronTriggerFactoryBean.setCronExpression("0 20 0 L * ?");
        return cronTriggerFactoryBean;
    }

}

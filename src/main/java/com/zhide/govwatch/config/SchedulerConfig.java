package com.zhide.govwatch.config;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {
    Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);
    @Value("${scheduling.enabled}")
    private boolean taskEnabled;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //线程池大小
        scheduler.setPoolSize(20);
        //线程名字前缀
        scheduler.setThreadNamePrefix("WatchTask");
        scheduler.initialize();
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (!taskEnabled) {
            //清空扫描到的定时任务即可
            taskRegistrar.setTriggerTasks(Maps.newHashMap());
            taskRegistrar.setCronTasks(Maps.newHashMap());
            taskRegistrar.setFixedRateTasks(Maps.newHashMap());
            taskRegistrar.setFixedDelayTasks(Maps.newHashMap());
        } else {
            List<CronTask> Cs = new ArrayList<>();
            List<String> Defines=new ArrayList<>();
            List<CronTask> tasks = taskRegistrar.getCronTaskList();
            for (CronTask task : tasks) {
                String name = task.getRunnable().toString();
                logger.info("Scan Task Define:" + name);
                if(Defines.contains(name)==false){
                    Defines.add(name);
                    Cs.add(task);
                } else {
                    logger.info(name+" has Define in Tasks,so Continue!");
                }
            }
            taskRegistrar.setCronTasksList(Cs);
        }
        taskRegistrar.setScheduler(taskScheduler());
    }
}

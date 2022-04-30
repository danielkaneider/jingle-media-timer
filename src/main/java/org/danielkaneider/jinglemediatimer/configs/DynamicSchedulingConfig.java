package org.danielkaneider.jinglemediatimer.configs;

import org.danielkaneider.jinglemediatimer.services.JingleScheduledService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    private final JingleScheduledService jingleScheduledService;

    public DynamicSchedulingConfig(JingleScheduledService jingleScheduledService) {
        this.jingleScheduledService = jingleScheduledService;
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(30);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        jingleScheduledService.addTasks(taskRegistrar);
    }

}
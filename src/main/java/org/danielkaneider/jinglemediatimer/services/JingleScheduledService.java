package org.danielkaneider.jinglemediatimer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JingleScheduledService {

    private static final Logger LOG = LoggerFactory.getLogger(JingleScheduledService.class);

    private final JingleService jingleService;
    private final JingleScheduler jingleScheduler;

    public JingleScheduledService(JingleService jingleService, JingleScheduler jingleScheduler) {
        this.jingleService = jingleService;
        this.jingleScheduler = jingleScheduler;
    }

    public void addTasks(ScheduledTaskRegistrar taskRegistrar) {
        LOG.info("Current time: {}", new Date());
        addStartJingleTask(taskRegistrar);
        addBeforeEndJingleTask(taskRegistrar);
    }

    private void addStartJingleTask(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                jingleService::runStartJingle,
                context -> {
                    Date nextExecutionTime = jingleScheduler.getNextStartExecutionTime();
                    LOG.info("Next start jingle: {}", nextExecutionTime);
                    return nextExecutionTime;
                }
        );
    }

    private void addBeforeEndJingleTask(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                jingleService::runBeforeEndJingle,
                context -> {
                    Date nextExecutionTime = jingleScheduler.getNextBeforeEndExecutionTime();
                    LOG.info("Next before end jingle: {}", nextExecutionTime);
                    return nextExecutionTime;
                }
        );
    }
}

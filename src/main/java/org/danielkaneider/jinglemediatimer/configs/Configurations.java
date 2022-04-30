package org.danielkaneider.jinglemediatimer.configs;

import org.danielkaneider.jinglemediatimer.components.JingleStartArguments;
import org.danielkaneider.jinglemediatimer.components.StartupArguments;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Bean
    public JingleStartArguments startupArguments(ApplicationArguments args, ConfigurableApplicationContext appContext) {
        return new StartupArguments(appContext).parse(args.getSourceArgs());
    }
}

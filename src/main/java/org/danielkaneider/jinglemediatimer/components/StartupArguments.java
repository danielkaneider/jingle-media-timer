package org.danielkaneider.jinglemediatimer.components;

import org.apache.commons.cli.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StartupArguments {

    private static final Options startOptions = new Options();

    static {

        Option breakOption = new Option("break", "break", true, "Break time between games [in minutes]");
        breakOption.setRequired(true);
        breakOption.setType(Number.class);

        Option durationOption = new Option("duration", "duration", true, "Duration of games [in minutes]");
        durationOption.setRequired(true);
        durationOption.setType(Number.class);

        startOptions.addOption(breakOption);
        startOptions.addOption(durationOption);
        startOptions.addRequiredOption("start", "start-time", true, "First start time [HH:mm]");
    }

    private final ConfigurableApplicationContext appContext;

    public StartupArguments(ConfigurableApplicationContext appContext) {
        this.appContext = appContext;
    }

    public JingleStartArguments parse(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(startOptions, args);

            Date startTime = new SimpleDateFormat("HH:mm").parse(cmd.getOptionValue("start"));
            Long durationInMinutes = (Long) cmd.getParsedOptionValue("duration");
            Long breakInMinutes = (Long) cmd.getParsedOptionValue("break");

            return new JingleStartArguments(startTime, durationInMinutes, breakInMinutes);
        } catch (Exception e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
            printHelp();
            System.exit(SpringApplication.exit(appContext));
            System.exit(0);
        }
        return null;
    }

    private void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("-start 09:00 -duration 30 -break 3", startOptions);
    }
}

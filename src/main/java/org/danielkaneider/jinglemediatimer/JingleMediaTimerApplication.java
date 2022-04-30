package org.danielkaneider.jinglemediatimer;

import org.apache.commons.cli.*;
import org.danielkaneider.jinglemediatimer.media.MediaController;
import org.danielkaneider.jinglemediatimer.services.JingleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class JingleMediaTimerApplication implements CommandLineRunner {

    private final MediaController jingle;
    private final JingleService jingleService;
    private final ConfigurableApplicationContext appContext;

    public JingleMediaTimerApplication(MediaController jingle, JingleService jingleService, ConfigurableApplicationContext appContext) {
        this.jingle = jingle;
        this.jingleService = jingleService;
        this.appContext = appContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(JingleMediaTimerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Options options = new Options();

        options.addOption("p", false, "Play");
        options.addOption("h", false, "Pause");
        options.addOption("start", false, "Play start jingle");
        options.addOption("end", false, "Play before end jingle");
        options.addOption("q", false, "Quit");

        HelpFormatter formatter = new HelpFormatter();
        System.out.println();
        formatter.printHelp("<for testing only>", options);

        boolean terminate = false;
        while (!terminate) {
            terminate = processInput(options);
        }

        System.exit(SpringApplication.exit(appContext));
    }

    private boolean processInput(Options options) throws ParseException {
        Scanner in = new Scanner(System.in);
        String nextLine = in.nextLine();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, nextLine.split(" "));

        if (cmd.hasOption('p')) {
            jingle.playPause();
            System.out.println("Play");
        } else if (cmd.hasOption('h')) {
            System.out.println("Pause");
            jingle.playPause();
        } else if (cmd.hasOption("start")) {
            jingleService.runStartJingle();
        } else if (cmd.hasOption("end")) {
            jingleService.runBeforeEndJingle();
        } else if (cmd.hasOption("q")) {
            return true;
        } else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ant", options);
        }

        return false;
    }
}

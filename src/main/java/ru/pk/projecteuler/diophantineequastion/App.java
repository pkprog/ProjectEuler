package ru.pk.projecteuler.diophantineequastion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import ru.pk.projecteuler.log.ConsoleLogger;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@Service
public class App implements CommandLineRunner {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

    private static final int MAX_D = 1000;

    public void run(String[] args) {
        ConsoleLogger.log("Start time:" + sdf.format(new Date()));
        Calc c = new Calc();
        ConsoleLogger.log("result: X=" + c.process(MAX_D));
        ConsoleLogger.log("End time:" + sdf.format(new Date()));
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}

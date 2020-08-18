package ru.pk.projecteuler.log;

import java.util.StringJoiner;

public class ConsoleLogger {

    public static void log(String... args) {
        if (args.length > 0) {
            StringJoiner sj = new StringJoiner(" ", "-->", "");
            for (String a: args) {
                sj.add(a);
            }
            System.out.println(sj.toString());
        }
    }

}

package ru.pk.projecteuler.largestprimefactor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppNotImportant {

    public static void main(String[] args) {
        Date startDate = new Date();
        System.out.println("Start time: " + new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(startDate));
        Calc c = new Calc();
        LongCollection resultCollection = c.processNotImportant(7830457L);
        System.out.println("result.size=" + resultCollection.size());
        Date endDate = new Date();
        System.out.println("End time: " + new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(endDate));
        System.out.println("Run time: " + (endDate.getTime() - startDate.getTime()) / 1000.);
    }

}

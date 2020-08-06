package ru.pk.projecteuler.largestprimefactor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App {

    public static void main(String[] args) {
        Date startDate = new Date();
        System.out.println("Start time: " + new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(startDate));
        Calc c = new Calc();
//        System.out.println("result=" + c.process(30));
        System.out.println("result=" + c.process(600851475143L));
//        System.out.println("result=" + c.process(13195000L));
        Date endDate = new Date();
        System.out.println("End time: " + new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(endDate));
        System.out.println("Run time: " + (endDate.getTime() - startDate.getTime()) / 1000.);
    }

}

package ru.pk.projecteuler.primepermutations;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        Calc c = new Calc();
        long[] threeNumbers = c.process();
        System.out.println("result=" + Arrays.toString(threeNumbers));
//        System.out.println("result test: " + c.testSplit(9827349794L, (byte) 10));
    }

}

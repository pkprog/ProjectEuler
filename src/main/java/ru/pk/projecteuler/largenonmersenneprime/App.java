package ru.pk.projecteuler.largenonmersenneprime;

public class App {

    public static void main(String[] args) {
//        int powerOfTwo = 32, digitsCount = 2;
//        int powerOfTwo = 6000, digitsCount = 2;
        int powerOfTwo = 7830457, digitsCount = 10;

        Calc c = new Calc();
        System.out.println("result=" + c.process(powerOfTwo, digitsCount));
    }

}

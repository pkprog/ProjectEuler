package ru.pk.projecteuler.largenonmersenneprime;

public class App {

    public static void main(String[] args) {
//        int powerOfTwo = 32, multiplier = 2, digitsCount = 2;
//        int powerOfTwo = 6000, multiplier = 10, digitsCount = 2;
        int powerOfTwo = 7830457, multiplier = 28433, digitsCount = 10;

        Calc c = new Calc();
        String calcResult = c.process(powerOfTwo, multiplier, digitsCount);
        if (calcResult.length() > 10) {
            System.out.println("result=" + calcResult.substring(calcResult.length() - 10));
        } else {
            System.out.println("result=" + calcResult);
        }

        //Не забыть в ответе прибавить +1
    }

}

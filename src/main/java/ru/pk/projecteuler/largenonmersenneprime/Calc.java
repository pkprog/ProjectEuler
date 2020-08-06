package ru.pk.projecteuler.largenonmersenneprime;

import java.math.BigInteger;

public class Calc {
    private final int CHECK_INTERVAL = 1000;
    private final int CHECK_LENGTH = 3000;
    private final int REMAIN_LENGTH = 2000;

    public String process(int powerOfTwo, int multiplier, int digitsCount) {
        BigInteger integer = new BigInteger("2");
        int j = 0; //Агент J
        for (int i = 2; i <= powerOfTwo; i++) {
            integer = integer.add(integer);
            if (j > CHECK_INTERVAL) {
                j = 0;
                String strNum = integer.toString();
                if (strNum.length() > CHECK_LENGTH) {
                    integer = new BigInteger(strNum.substring(strNum.length() - REMAIN_LENGTH));
                }
            }
            j++;
        }

        System.out.println("Intermediate result: BigInteger=" + integer.toString());

        BigInteger mult = new BigInteger(integer.toString());
        int k = 0; //Агент K
        for (int i = 2; i <= multiplier; i++) {
            mult = mult.add(integer);
            if (k > CHECK_INTERVAL) {
                k = 0;
                String strNum = mult.toString();
                if (strNum.length() > CHECK_LENGTH) {
                    mult = new BigInteger(strNum.substring(strNum.length() - REMAIN_LENGTH));
                }
            }
            k++;
        }

        System.out.println("Final result: BigInteger=" + mult.toString());

        return mult.toString();
    }

}

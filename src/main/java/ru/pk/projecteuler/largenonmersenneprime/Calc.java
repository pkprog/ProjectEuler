package ru.pk.projecteuler.largenonmersenneprime;

import java.math.BigInteger;

public class Calc {
    private final int CHECK_INTERVAL = 1000;
    private final int CHECK_LENGTH = 3000;
    private final int REMAIN_LENGTH = 2000;

    public long process(int powerOfTwo, int digitsCount) {
        BigInteger integer = new BigInteger("2");
        int j = 0;
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

        System.out.println("BigInteger=" + integer.toString());

        return 0;
    }

}

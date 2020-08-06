package ru.pk.projecteuler.largenonmersenneprime;

public class Calc {

    public long process(int powerOfTwo, int digitsCount) {
        long tempResult = 2;
        for (int i = 2; i <= powerOfTwo; i++) {
            tempResult = tempResult << 1;
        }

        return tempResult;
    }

}

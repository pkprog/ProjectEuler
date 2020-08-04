package ru.pk.projecteuler.mult3and5;

public class Calc {

    public long process(int max) {
        long result = 0;
        for (int i = 1; i <= max; i++) {
            boolean isSuitable = false;
            if (i % 3 == 0) isSuitable = true;
            else if (i % 5 == 0) isSuitable = true;

            if (isSuitable) result += i;
        }
        return result;
    }

}

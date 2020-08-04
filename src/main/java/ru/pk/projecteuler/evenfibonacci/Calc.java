package ru.pk.projecteuler.evenfibonacci;

public class Calc {

    public long process(int maxFibnumberValue) {
        long resultEvenSumm = 0;

        int prev1 = 1, prev2 = 2;
        int nextValue = 0;
        resultEvenSumm += (prev1 % 2 == 0) ? prev1 : 0;
        resultEvenSumm += (prev2 % 2 == 0) ? prev2 : 0;

        do {
            if (nextValue % 2 == 0) {
                resultEvenSumm += nextValue;
            }
            nextValue = prev1 + prev2;
            prev1 = prev2;
            prev2 = nextValue;
        } while(nextValue <= maxFibnumberValue);

        return resultEvenSumm;
    }

}

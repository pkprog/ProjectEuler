package ru.pk.projecteuler.largestprimefactor;

import ru.pk.projecteuler.lib.FindPrimeNumbers;
import ru.pk.projecteuler.lib.LongCollection;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class Calc {

    public long process(long number) {
        long tempNumber = number;
        LongCollection primeFactors = new LongCollection();

        for (long i = 2; i <= tempNumber; i++) {
            LongCollection collection = FindPrimeNumbers.findPrimeNumbers(i);
            LongCollection.Iterator it = collection.iterator();
            while (it.hasNext()) {
                long testPrimeFactor = it.next();
                if (tempNumber % testPrimeFactor == 0) {
                    primeFactors.add(testPrimeFactor);
                    tempNumber = tempNumber / testPrimeFactor;
                    it.reset();
                }
            }
        }

        Collection<Long> resultList = primeFactors.getCollection();
        Optional<Long> result = resultList.stream().max(Comparator.comparingLong(Long::longValue));
        return result.isPresent() ? result.get() : 0;
    }


    public LongCollection processNotImportant(long max) {
        return FindPrimeNumbers.findPrimeNumbers(max);
    }
}

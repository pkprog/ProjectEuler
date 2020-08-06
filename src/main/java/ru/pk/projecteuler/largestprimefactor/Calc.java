package ru.pk.projecteuler.largestprimefactor;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class Calc {

    public long process(long number) {
        long tempNumber = number;
        LongCollection primeFactors = new LongCollection();

        for (long i = 2; i <= tempNumber; i++) {
            LongCollection collection = findPrimeNumbers(i);
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

    /**
     * Поиск простых чисел до указанного максимального числа
     */
    private LongCollection findPrimeNumbers(long max) {
        LongCollection primeNumbers = new LongCollection();
        LongCollection.Iterator it = primeNumbers.iterator();

        //Для малых чисел
        for (long i = 2; i <= max && i <= 8; i++) {
            it.reset();
            boolean remainder = false;
            while (it.hasNext()) {
                if (i % it.next() == 0) {
                    remainder = true;
                    break;
                }
            }
            //Проверили деление, но везде остаток
            if (!remainder) {
                primeNumbers.add(i);
            }
        }

        for (long i = 9; i <= max; i += 2) {
            byte rem = (byte) (i % 10);
            if (rem == 5) {
                continue;
            }

            it.reset();
            boolean remainder = false;
            while (it.hasNext()) {
                if (i % it.next() == 0) {
                    remainder = true;
                    break;
                }
            }
            //Проверили деление, но везде остаток
            if (!remainder) {
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }

    public LongCollection processNotImportant(long max) {
        return findPrimeNumbers(max);
    }
}

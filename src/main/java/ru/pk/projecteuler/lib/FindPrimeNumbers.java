package ru.pk.projecteuler.lib;

/**
 * Поиск простых чисел до указанного максимального числа
 */
public class FindPrimeNumbers {

    public static LongCollection findPrimeNumbers(long max) {
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

}

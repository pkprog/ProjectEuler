package ru.pk.projecteuler.primepermutations;

import ru.pk.projecteuler.lib.FindPrimeNumbers;
import ru.pk.projecteuler.lib.LongCollection;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringJoiner;

public class Calc {
    private static final long MAX_NUMBER = 9999L;

    public long process() {
        long result = 0;

        LongCollection collection = FindPrimeNumbers.findPrimeNumbers(MAX_NUMBER);
        Collection<Long> temp = collection.getCollection();
        Collection<SplitDigits> spList = new LinkedList<>();
        for (Long l: temp) {
            SplitDigits sp = SplitDigits.split(l, (byte) 4);
            if (sp != null) spList.add(sp);
        }

        return result;
    }

    public String testSplit(long number, byte digits) {
        return SplitDigits.split(number, digits).toString();
    }

    private static class SplitDigits {
        Long number;
        byte[] digits;

        private SplitDigits(Long number, byte[] digits) {
            this.number = number;
            this.digits = digits;
        }

        /**
         * Создать новый объект из числа, одновременно разбив его на цифры
         * @param number Число для разбивки на цифры
         * @param numberDiigtsFilter Число цифр, которое подходит
         * @return null, если объект не создан, в противном случае - созданный объект
         */
        public static SplitDigits split(Long number, byte numberDiigtsFilter) {
            byte[] digits = new byte[numberDiigtsFilter];
            long operatingNumber = number;
            for (byte i = 1; i <= numberDiigtsFilter; i++) {
                if (operatingNumber == 0) return null;
                final long TEN = 10;
                digits[i-1] = (byte) (operatingNumber % TEN);
                operatingNumber = operatingNumber / TEN;
            }
            SplitDigits obj = new SplitDigits(number, digits);
            return obj;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", SplitDigits.class.getSimpleName() + "[", "]")
                    .add("number=" + number)
                    .add("digits=" + Arrays.toString(digits))
                    .toString();
        }
    }

}

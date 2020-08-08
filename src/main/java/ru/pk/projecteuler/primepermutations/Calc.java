package ru.pk.projecteuler.primepermutations;

import ru.pk.projecteuler.lib.DigitsEquals;
import ru.pk.projecteuler.lib.FindPrimeNumbers;
import ru.pk.projecteuler.lib.LongCollection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Calc {
    private static final long MAX_NUMBER = 9999L;

    public long[] process() {
        long[] result = new long[3];

        LongCollection collection = FindPrimeNumbers.findPrimeNumbers(MAX_NUMBER);
        Collection<Long> temp = collection.getCollection();
        Collection<SplitDigits> spList = new LinkedList<>();
        for (Long l: temp) {
            Optional<SplitDigits> spOptional = SplitDigits.split(l, (byte) 4);
            spOptional.ifPresent(spList::add);
        }
        spList = filterAllDiff(spList);
        Collection<GroupSameDigits> groupsRaw = group(spList);
        Collection<GroupSameDigits> groupsRaw2 = groupsRaw.stream().filter(g -> g.getSpList().size() > 1).collect(Collectors.toList());
        //По условию задачи нужно 3 числа
        Collection<GroupSameDigits> groups = groupsRaw2.stream().filter(g -> g.getSpList().size() >= 3).collect(Collectors.toList());

        return result;
    }

    /**
     * Фильтр чисел, где все цифры разные
     */
    private Collection<SplitDigits> filterAllDiff(Collection<SplitDigits> spList) {
        Collection<SplitDigits> result = new LinkedList<>();
        for (SplitDigits sp: spList) {
            Set<Byte> set = new HashSet<>();
            for (byte i = 0; i < sp.getDigits().length; i++) {
                set.add(sp.getDigits()[i]);
            }
            if (set.size() == sp.getDigits().length) {
                result.add(sp);
            }
        }
        return result;
    }

    private Collection<GroupSameDigits> group(Collection<SplitDigits> spList) {
        Collection<GroupSameDigits> groups = new LinkedList<>();
        for (SplitDigits sp: spList) {
            GroupSameDigits foundGroup = null;
            for (GroupSameDigits gr: groups) {
                if (gr.isMyGroup(sp)) {
                    foundGroup = gr;
                    break;
                }
            }
            if (foundGroup != null) {
                foundGroup.add(sp);
            } else {
                foundGroup = new GroupSameDigits(sp);
                groups.add(foundGroup);
            }
        }
        return groups;
    }

    public String testSplit(long number, byte digits) {
        return SplitDigits.split(number, digits).toString();
    }

    /**
     * Разбивка числа на цифры
     */
    private static class SplitDigits {
        private Long number;
        private byte[] digits;

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
        public static Optional<SplitDigits> split(Long number, byte numberDiigtsFilter) {
            byte[] digits = new byte[numberDiigtsFilter];
            long operatingNumber = number;
            for (byte i = 1; i <= numberDiigtsFilter; i++) {
                if (operatingNumber == 0) return Optional.empty(); //Число цифр в числе меньше, чем в фильтре
                final long TEN = 10;
                digits[i-1] = (byte) (operatingNumber % TEN);
                operatingNumber = operatingNumber / TEN;
            }
            if (operatingNumber != 0) return Optional.empty();//Число цифр в числе больше, чем в фильтре

            //Развернуть
            byte[] digitsResult = new byte[digits.length];
            for (int i = digits.length-1; i >= 0 ; i--) {
                digitsResult[digits.length-1 - i] = digits[i];
            }
            SplitDigits obj = new SplitDigits(number, digitsResult);
            return Optional.of(obj);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", SplitDigits.class.getSimpleName() + "[", "]")
                    .add("number=" + number)
                    .add("digits=" + Arrays.toString(digits))
                    .toString();
        }

        public Long getNumber() {
            return number;
        }

        public byte[] getDigits() {
            return digits;
        }
    }

    /**
     * Группа чисел с одинаковым множеством цифр
     */
    private static class GroupSameDigits {
        private byte[] digits;
        Collection<SplitDigits> spList = new HashSet<>();

        public GroupSameDigits(SplitDigits splitDigits) {
            this.digits = splitDigits.getDigits();
            Arrays.sort(this.digits);
            this.spList.add(splitDigits);
        }

        /**
         * Проверка, подходит ли эта группа данному числу
         */
        public boolean isMyGroup(SplitDigits sp) {
            return DigitsEquals.equals(this.digits, sp.getDigits());
        }

        public void add(SplitDigits sp) {
            if (!DigitsEquals.equals(this.digits, sp.getDigits())) {
                throw new IllegalArgumentException("Попытка добавления в группу числа в другими цифрами");
            }
            this.spList.add(sp);
        }

        public Collection<SplitDigits> getSpList() {
            return Collections.unmodifiableCollection(this.spList);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GroupSameDigits that = (GroupSameDigits) o;

            return DigitsEquals.equals(this.digits, that.digits);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(digits);
        }
    }
}

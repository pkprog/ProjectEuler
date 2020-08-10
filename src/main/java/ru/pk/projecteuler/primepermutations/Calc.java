package ru.pk.projecteuler.primepermutations;

import ru.pk.projecteuler.lib.DigitsEquals;
import ru.pk.projecteuler.lib.FindPrimeNumbers;
import ru.pk.projecteuler.lib.LongCollection;

import java.util.*;
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
        Collection<GroupSameDigits> groupsRaw3 = groupsRaw2.stream().filter(g -> g.getSpList().size() >= 3).collect(Collectors.toList());
        Collection<GroupSameDigitsWithSeq> seqGroups = filterGroupWithSeq(groupsRaw3, 3);
        System.out.println("Groups found count = " + seqGroups.size());

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

    /**
     * Группировка по множеству цифр. (Сортируются перед проверкой)
     */
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

    /**
     * Фильтр групп и чисел в них, где найдена последовательность
     * @param groups Нефильтрованные группы
     * @param targetSeq число чисел в последовательности
     * @return Группа
     */
    private Collection<GroupSameDigitsWithSeq> filterGroupWithSeq(Collection<GroupSameDigits> groups, int targetSeq) {
        Collection<GroupSameDigitsWithSeq> result = new LinkedList<>();
        for (GroupSameDigits g: groups) {
            List<SplitDigits> sorted = g.getSpList().stream().sorted(Comparator.comparingLong(SplitDigits::getNumber)).collect(Collectors.toList());

            //Таблица пересечений-инкрементов
            IncrementPairs[][] arrInc = new IncrementPairs[sorted.size()][sorted.size()];
            for (int i = 0; i < arrInc.length; i++) {
                for (int j = 0; j < arrInc[i].length; j++) {
                    long increment = sorted.get(i).getNumber() - sorted.get(j).getNumber();
                    if (sorted.get(i).getNumber() <= sorted.get(j).getNumber()) {
                        arrInc[i][j] = new IncrementPairs(increment, sorted.get(i), sorted.get(j));
                    } else {
                        arrInc[i][j] = new IncrementPairs(increment, sorted.get(j), sorted.get(i));
                    }
                }
            }
            //Массив<Инкремент, Количество> - подсчет числа инкрементов
            Map<Long, Integer> incCnt = new HashMap<>();
            for (int i = 0; i < arrInc.length; i++) {
                for (int j = 0; j < arrInc[i].length; j++) {
                    if (arrInc[i][j].getIncrenent() <= 0) continue;
                    //
                    if (incCnt.containsKey(arrInc[i][j].getIncrenent())) {
                        Integer newCount = incCnt.get(arrInc[i][j].getIncrenent()) + 1;
                        incCnt.put(arrInc[i][j].getIncrenent(), newCount);
                    } else {
                        incCnt.put(arrInc[i][j].getIncrenent(), 1);
                    }
                }
            }

            for (Map.Entry<Long, Integer> e: incCnt.entrySet()) {
                final long increment = e.getKey();
                final int count = e.getValue();
                if (count > 0 && increment > 0) {
                    Collection<IncrementPairs> potentialSequenced = new HashSet<>();
                    for (int i = 0; i < arrInc.length; i++) {
                        for (int j = 0; j < arrInc[i].length; j++) {
                            if (arrInc[i][j].getIncrenent() == increment) {
                                potentialSequenced.add(arrInc[i][j]);
                            }
                        }
                    }
                    if (potentialSequenced.size() > 1) {
                        Collection<SplitDigits> sequenced;
                        int i = 1;
                        while(true) {
                            sequenced = makeSequence(potentialSequenced, i);
                            if (sequenced != null && sequenced.size() == targetSeq) break;
                            i++;
                            if (i > potentialSequenced.size()) break;
                        }

                        //Последовательность найдена
                        if (sequenced != null && sequenced.size() > 0) {
                            GroupSameDigitsWithSeq resultGroup = new GroupSameDigitsWithSeq(increment, sequenced);
                            result.add(resultGroup);
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * Построить поселдовательность из переданных чисел. В порядке возрастания
     * @param pairs Набор для построения
     * @param seqNumber Номер построенной последовательности. Вдруг их несколько, вот можно получить не первую попавшуюся
     * @return Последовательность
     */
    private List<SplitDigits> makeSequence(Collection<IncrementPairs> pairs, int seqNumber) {
        Collection<IncrementPairs> sorted = pairs.stream()
                .sorted(Comparator.comparingLong(p -> p.getS1().getNumber()))
                .collect(Collectors.toList());
        
        List<IncrementPairs> resultPairs = new LinkedList<>();
        IncrementPairs prevPair = null;
        int i = 1;
        for (IncrementPairs p: sorted) {
            if (prevPair == null) {
                prevPair = p;
                resultPairs.add(p);
            } else if (prevPair.getS2().equals(p.getS1())) {
                resultPairs.add(p);
                prevPair = p;
            } else {
                if (resultPairs.size() > 1) { //Какая-то последовательность построена
                    if (i >= seqNumber) {
                        break;
                    } else { //(i < seqNumber)
                        i++;
                        resultPairs.clear();
                        resultPairs.add(p);
                        prevPair = p;
                    }
                }
            }
        }

        if (i == seqNumber) {
            List<SplitDigits> result = new LinkedList<>();
            result.add(resultPairs.get(0).getS1());
            for (IncrementPairs tempPair: resultPairs) {
                result.add(tempPair.getS2());
            }
            return result;
        }

        return null;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SplitDigits that = (SplitDigits) o;

            return getNumber().equals(that.getNumber());
        }

        @Override
        public int hashCode() {
            return getNumber().hashCode();
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

    private static class GroupSameDigitsWithSeq extends GroupSameDigits {
        private long seq;

        public GroupSameDigitsWithSeq(long seq, Collection<SplitDigits> spList) {
            super(spList.iterator().next());
            Iterator<SplitDigits> it = spList.iterator();
            it.next();
            while (it.hasNext()) {
                this.add(it.next());
            }
            this.seq = seq;
        }

        public long getSeq() {
            return seq;
        }

        public void setSeq(long seq) {
            this.seq = seq;
        }
    }

    private static class IncrementPairs {
        private long increnent;
        private SplitDigits s1;
        private SplitDigits s2;

        public IncrementPairs(long increnent, SplitDigits s1, SplitDigits s2) {
            this.increnent = increnent;
            this.s1 = s1;
            this.s2 = s2;
        }

        public long getIncrenent() {
            return increnent;
        }

        public SplitDigits getS1() {
            return s1;
        }

        public SplitDigits getS2() {
            return s2;
        }
    }
}

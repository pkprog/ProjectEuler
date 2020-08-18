package ru.pk.projecteuler.pokerhands.objects.combination;

import ru.pk.projecteuler.log.ConsoleLogger;
import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringJoiner;

/**
 * Комбинация карт
 */
public abstract class Combination implements Comparable<Combination> {
    protected final Hand hand;
    public abstract ConbinationRank combinationRank();

    public Combination(Hand hand) {
        this.hand = hand;
    }

    public Set<Card> getCards() {
        return hand.getCards();
    }

    protected Hand getHand() {
        return hand;
    }

    @Override
    public int compareTo(Combination o) {
        return compareToAddText(o, null);
    }

    private int compareToAddText(Combination o, List<String> resultText) {
        if (!combinationRank().equals(o.combinationRank())) {
            final String text = "Разные комбинации: " + combinationRank().getDscRu() + " - " + o.combinationRank().getDscRu() + ".";
            boolean compareRankResult = combinationRank().getValue() > o.combinationRank().getValue();
            int result;
            if (compareRankResult) {
                if (resultText != null) {
                    resultText.add(text);
                    resultText.add("Выиграл [1].");
                }
                result = 1;
            } else {
                if (resultText != null) {
                    resultText.add(text);
                    resultText.add("Выиграл [2].");
                }
                result = -1;
            }
            if (resultText != null) {
                resultText.add(getExactCards());
                resultText.add("<->");
                resultText.add(o.getExactCards());
            }
            return result;
        } else {
            final String text = "Одинаковая комбинация: " + combinationRank().getDscRu() + ". Тест по большей карте.";
            int temp = compareCardsTo(o);
            int compareResult = Integer.compare(temp, 0);
            if (compareResult > 0) {
                if (resultText != null) {
                    resultText.add(text);
                    resultText.add("Выиграл [1].");
                }
            } else if (compareResult < 0) {
                if (resultText != null) {
                    resultText.add(text);
                    resultText.add("Выиграл [2].");
                }
            } else {
                if (resultText != null) {
                    resultText.add(text);
                    resultText.add("Ничья.");
                }
            }
            if (resultText != null) {
                resultText.add(getExactCards());
                resultText.add("<->");
                resultText.add(o.getExactCards());
            }
            return compareResult;
        }
    }

    /**
     * Сравнение с выводои информации о комбинациях
     * Алгоритм такой же, как в compareTo
     */
    public int compareToInform(Combination o) {
        List<String> text = new LinkedList<>();
        int result = compareToAddText(o, text);
        ConsoleLogger.log(text.toArray(new String[]{}));
        return result;
    }

    /**
     * Сравнить
     * @param o
     * @return
     */
    protected abstract int compareCardsTo(Combination o);

    /**
     * Конкретные карты данной комбинации
     */
    protected abstract SortedSet<Card> exactCards();

    /**
     * Конкретные карты данной комбинации. Пользовательский метод
     */
    public String getExactCards() {
        StringJoiner sj = new StringJoiner(" ", "{", "}");
        for (Card c: exactCards()) {
            sj.add(c.getRank().getShortNameRu() + c.getSuit().getShortNameRu());
        }
        return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Combination that = (Combination) o;

        return combinationRank() == that.combinationRank() && compareCardsTo(that) == 0;
    }

    @Override
    public int hashCode() {
        return getHand().hashCode();
    }
}

package ru.pk.projecteuler.pokerhands.objects.combination;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;

import java.util.Set;

/**
 * Комбинация карт
 */
public abstract class Combination implements Comparable<Combination> {
    protected final Hand hand;
    protected abstract ConbinationRank combinationRank();

    public Combination(Hand hand) {
        this.hand = hand;
    }

    @Override
    public int compareTo(Combination o) {
        if (!combinationRank().equals(o.combinationRank())) {
            return combinationRank().getValue() > o.combinationRank().getValue() ? 1 : -1;
        } else {
            int temp = compareCardsTo(o);
            return Integer.compare(temp, 0);
        }
    }

    protected abstract int compareCardsTo(Combination o);

    public Set<Card> getCards() {
        return hand.getCards();
    }

    protected Hand getHand() {
        return hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Combination that = (Combination) o;

        return combinationRank() == that.combinationRank() && getHand().equals(that.getHand());
    }

    @Override
    public int hashCode() {
        return getHand().hashCode();
    }
}

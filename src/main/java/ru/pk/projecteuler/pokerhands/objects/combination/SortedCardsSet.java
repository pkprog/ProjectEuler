package ru.pk.projecteuler.pokerhands.objects.combination;

import ru.pk.projecteuler.pokerhands.objects.Card;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Сортировка карт от большей к меньшей (по убыванию)
 */
public class SortedCardsSet extends TreeSet<Card> {
    private SortedCardsSet(Comparator<Card> comparator) {
        super(comparator);
    }

    public static SortedCardsSet createSortedRank() {
        return new SortedCardsSet(comparatorByRank);
    }

    public static SortedCardsSet createSortedSuit() {
        return new SortedCardsSet(comparatorBySuit);
    }

    public static SortedCardsSet createSortedRankAndThanSortedSuit() {
        return new SortedCardsSet(comparatorByRankThenSuit);
    }

    public SortedCardsSet addCard(Card card) {
        super.add(card);
        return this;
    }

    public SortedCardsSet addCards(Collection<Card> cards) {
        super.addAll(cards);
        return this;
    }

    private static final Comparator<Card> comparatorByRank;
    static {
        Comparator<Card> tmp1 = Comparator.comparing(Card::getRank, Comparator.reverseOrder());
        comparatorByRank = tmp1.thenComparing(Card::getSuit, Comparator.reverseOrder());
    }
    private static final Comparator<Card> comparatorBySuit = Comparator.comparing(Card::getSuit, Comparator.reverseOrder());
    private static final Comparator<Card> comparatorByRankThenSuit;
    static {
        Comparator<Card> tmp1 = Comparator.comparing(Card::getRank, Comparator.reverseOrder());
        comparatorByRankThenSuit = tmp1.thenComparing(Card::getSuit, Comparator.reverseOrder());
    }
}

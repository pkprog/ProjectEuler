package ru.pk.projecteuler.pokerhands.objects.combination;

import ru.pk.projecteuler.pokerhands.objects.Card;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class SortedCardsSet extends TreeSet<Card> {
    private SortedCardsSet(Comparator<Card> comparator) {
        super(comparator);
    }

    public static SortedCardsSet createSorttedRank() {
        return new SortedCardsSet(comparatorByRank);
    }

    public static SortedCardsSet createSorttedSuit() {
        return new SortedCardsSet(comparatorBySuit);
    }

    public SortedCardsSet addCard(Card card) {
        super.add(card);
        return this;
    }

    public SortedCardsSet addCards(Collection<Card> cards) {
        super.addAll(cards);
        return this;
    }

    private static final Comparator<Card> comparatorByRank = Comparator.comparing(Card::getRank).reversed();
    private static final Comparator<Card> comparatorBySuit = Comparator.comparing(Card::getSuit).reversed();
}

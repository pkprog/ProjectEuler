package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

/**
 * High Card: Highest value card
 * Старшая Карта – максимальная по рангу
 */
public class HighCard extends Combination {
    public HighCard(Hand hand) {
        super(hand);
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.high_card;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof HighCard) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        List<Card> cards = new ArrayList<>(hand.getCards());
        cards.sort(Comparator.comparing(Card::getRank).reversed());

        List<Card> cards2 = new ArrayList<>(o.getCards());
        cards.sort(Comparator.comparing(Card::getRank).reversed());

        return cards.get(0).getRank().compare(cards2.get(0).getRank());
    }

    @Override
    public SortedSet<Card> exactCards() {
        List<Card> cards = new ArrayList<>(hand.getCards());
        cards.sort(Comparator.comparing(Card::getRank).reversed());
        return SortedCardsSet.createSortedRank().addCard(cards.get(0));
    }
}

package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.Collection;
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

        Collection<Card> cards = SortedCardsSet.createSortedRank().addCards(hand.getCards());
        Collection<Card> cards2 = SortedCardsSet.createSortedRank().addCards(o.getCards());

        return cards.iterator().next().getRank().compare(cards2.iterator().next().getRank());
    }

    @Override
    public SortedSet<Card> exactCards() {
        Collection<Card> cards = SortedCardsSet.createSortedRank().addCards(hand.getCards());
        return SortedCardsSet.createSortedRank().addCard(cards.iterator().next());
    }
}

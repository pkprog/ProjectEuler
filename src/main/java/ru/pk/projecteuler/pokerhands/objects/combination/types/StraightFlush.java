package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.IllegalCombinationException;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;

/**
 * Straight Flush: All cards are consecutive values of same suit.
 * Стрит-Флеш – все в масть по порядку
 */
public class StraightFlush extends Combination {
    private final SortedSet<Card> cardsInStraight;

    public StraightFlush(Hand hand) throws IllegalCombinationException {
        super(hand);
        this.cardsInStraight = Collections.unmodifiableSortedSet(calc(hand.getCards()));
        if (cardsInStraight.size() == 0) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        } else {
            if (!CardsSetUtils.checkFlush(this.cardsInStraight)) {
                throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
            }
        }
    }

    private SortedSet<Card> calc(Set<Card> cards) {
        return StraightCombinationUtils.calc(cards);
    }

    public SortedSet<Card> getCardsInStraight() {
        return cardsInStraight;
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.straight_flush;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof StraightFlush) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        StraightFlush that = (StraightFlush) o;

        return this.cardsInStraight.first().getRank().compare(that.getCardsInStraight().first().getRank());
    }

    @Override
    protected SortedSet<Card> exactCards() {
        return this.cardsInStraight;
    }
}

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
 * Straight: All cards are consecutive values
 * Стрит – подряд, но не одной масти
 */
public class Straight extends Combination {
    private final SortedSet<Card> cardsInStraight;

    public Straight(Hand hand) throws IllegalCombinationException {
        super(hand);
        //Проверить Стрит
        this.cardsInStraight = Collections.unmodifiableSortedSet(calc(hand.getCards()));
        if (cardsInStraight.size() == 0) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
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
        return CombinationRank.straight;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof Straight) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        Straight that = (Straight) o;

        return this.cardsInStraight.first().getRank().compare(that.getCardsInStraight().first().getRank());
    }

    @Override
    protected SortedSet<Card> exactCards() {
        return this.cardsInStraight;
    }
}

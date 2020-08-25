package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.Rank;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.IllegalCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

/**
 * Four of a Kind: Four cards of the same value.
 * Каре – четыре карты одного достоинства
 */
public class FourOfAKind extends Combination {
    private final Set<Card> cardsInFour;

    public FourOfAKind(Hand hand) throws IllegalCombinationException {
        super(hand);
        //Вычислить четвёрку
        this.cardsInFour = Collections.unmodifiableSet(calc(hand.getCards()));
        if (this.cardsInFour.size() != 4) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    /**
     * Здесь вычисляется нвибольшая из червёрок (по рангу)
     */
    private Set<Card> calc(Set<Card> cards) {
        Set<Card> prev = null;
        Rank prevRank = null;

        for (Set<Card> th: CardsSetUtils.getAllFour(cards)) {
            if (th.size() == 0) continue;

            Card[] threeCards = th.toArray(new Card[] {});
            Card c1 = threeCards[0], c2 = threeCards[1], c3 = threeCards[2], c4 = threeCards[3];

            if (prevRank == null) {
                prevRank = c1.getRank();
                prev = new HashSet<>(Arrays.asList(c1, c2, c3, c4));
            } else {
                if (prevRank.getValue() < c2.getRank().getValue()) {
                    prevRank = c1.getRank();
                    prev = new HashSet<>(Arrays.asList(c1, c2, c3, c4));
                }
            }
        }

        return prev == null ? Collections.emptySet() : prev;
    }

    public Set<Card> getCardsInFour() {
        return cardsInFour;
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.four_of_a_kind;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof FourOfAKind) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        Card c1 = this.cardsInFour.iterator().next();
        Card c2 = ((FourOfAKind)o).getCardsInFour().iterator().next();

        return c1.getRank().compare(c2.getRank());
    }

    @Override
    protected SortedSet<Card> exactCards() {
        return SortedCardsSet.createSortedSuit().addCards(this.cardsInFour);
    }
}

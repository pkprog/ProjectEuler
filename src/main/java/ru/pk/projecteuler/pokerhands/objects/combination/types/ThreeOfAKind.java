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
 * Three of a Kind: Three cards of the same value
 * Тройка (Трипс, Сет) – три карты одного номинала
 */
public class ThreeOfAKind extends Combination {
    private final Set<Card> cardsInThree;

    public ThreeOfAKind(Hand hand) throws IllegalCombinationException {
        super(hand);
        //Вычислить тройку
        this.cardsInThree = Collections.unmodifiableSet(calc(hand.getCards()));
        if (this.cardsInThree.size() != 3) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    private Set<Card> calc(Set<Card> cards) {
        Set<Card> prev = null;
        Rank prevRank = null;

        for (Set<Card> th: CardsSetUtils.getAllThree(cards)) {
            if (th.size() == 0) continue;

            Card[] threeCards = th.toArray(new Card[] {});
            Card c1 = threeCards[0], c2 = threeCards[1], c3 = threeCards[2];

            if (prevRank == null) {
                prevRank = c1.getRank();
                prev = new HashSet<>(Arrays.asList(c1, c2, c3));
            } else {
                if (prevRank.getValue() < c2.getRank().getValue()) {
                    prevRank = c1.getRank();
                    prev = new HashSet<>(Arrays.asList(c1, c2, c3));
                }
            }
        }

        return prev == null ? Collections.emptySet() : prev;
    }

    public Set<Card> getCardsInThree() {
        return cardsInThree;
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.three_of_a_kind;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof ThreeOfAKind) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        Card c1 = this.cardsInThree.iterator().next();
        Card c2 = ((ThreeOfAKind)o).getCardsInThree().iterator().next();

        return c1.getRank().compare(c2.getRank());
    }

    @Override
    protected SortedSet<Card> exactCards() {
        return SortedCardsSet.createSortedSuit().addCards(this.cardsInThree);
    }
}

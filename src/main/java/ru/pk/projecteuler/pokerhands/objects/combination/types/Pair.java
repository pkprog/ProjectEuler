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
 * One Pair: Two cards of the same value
 * 2 карты, у которых одинаковый ранг
 */
public class Pair extends Combination {
    private final Set<Card> cardsInPair;

    public Pair(Hand hand) throws IllegalCombinationException {
        super(hand);
        //Вычислить пару
        this.cardsInPair = Collections.unmodifiableSet(calc(hand.getCards()));
        if (this.cardsInPair.size() != 2) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    private Set<Card> calc(Set<Card> cards) {
        Set<Card> prevPair = null;
        Rank prevRank = null;

        for (Set<Card> pair: CardsSetUtils.getAllPairs(cards)) {
            Card[] pairCards = pair.toArray(new Card[] {});
            Card c1 = pairCards[0], c2 = pairCards[1];

            if (prevRank == null) {
                prevRank = c1.getRank();
                prevPair = new HashSet<>(Arrays.asList(c1, c2));
            } else {
                if (prevRank.getValue() < c2.getRank().getValue()) {
                    prevRank = c1.getRank();
                    prevPair = new HashSet<>(Arrays.asList(c1, c2));
                }
            }
        }

        return prevPair == null ? Collections.emptySet() : prevPair;
    }

    public Set<Card> getCardsInPair() {
        return cardsInPair;
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.one_pair;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof Pair) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        Card c1 = this.cardsInPair.iterator().next();
        Card c2 = ((Pair)o).getCardsInPair().iterator().next();

        return c1.getRank().compare(c2.getRank());
    }

    @Override
    protected SortedSet<Card> exactCards() {
        return SortedCardsSet.createSortedSuit().addCards(this.cardsInPair);
    }
}

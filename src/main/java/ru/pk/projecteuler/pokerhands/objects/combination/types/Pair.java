package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.Rank;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.ConbinationRank;
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
        cardsInPair = Collections.unmodifiableSet(calc(hand.getCards()));
        if (cardsInPair.size() != 2) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    private Set<Card> calc(Set<Card> cards) {
        Set<Card> prevPair = null;
        Rank prevRank = null;
        for (Card c1: cards) {
            for (Card c2: cards) {
                if (c1.equals(c2)) continue;
                if (c1.getRank().equals(c2.getRank())) {
                    if (prevRank == null) {
                        prevRank = c2.getRank();
                        prevPair = new HashSet<>(Arrays.asList(c1, c2));
                    } else {
                        if (prevRank.getValue() < c2.getRank().getValue()) {
                            prevRank = c2.getRank();
                            prevPair = new HashSet<>(Arrays.asList(c1, c2));
                        }
                    }
                }
            }
        }

        return prevPair == null ? Collections.emptySet() : prevPair;
    }

    public Set<Card> getCardsInPair() {
        return cardsInPair;
    }

    @Override
    public ConbinationRank combinationRank() {
        return ConbinationRank.one_pair;
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
        return SortedCardsSet.createSorttedSuit().addCards(this.cardsInPair);
    }
}

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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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
            if (pair.size() == 0) continue;

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

        int test1 = c1.getRank().compare(c2.getRank());
        if (test1 == 0) {
            Collection<Card> thisCards = SortedCardsSet.createSortedRank().addCards(this.hand.getCards());
            Collection<Card> thatCards = SortedCardsSet.createSortedRank().addCards(o.getCards());

            Iterator<Card> itThis2 = thisCards.iterator();
            Iterator<Card> itThat2 = thatCards.iterator();
            while (itThis2.hasNext()) {
                int res = itThis2.next().getRank().compare(itThat2.next().getRank());
                if (res != 0) {
                    return res;
                }
            }

            return 0;
        }
        return test1;
    }

    @Override
    protected SortedSet<Card> exactCards() {
        return SortedCardsSet.createSortedSuit().addCards(this.cardsInPair);
    }
}

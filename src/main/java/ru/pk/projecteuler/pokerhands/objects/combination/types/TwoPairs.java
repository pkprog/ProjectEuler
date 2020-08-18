package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.IllegalCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * Two Pairs: Two different pairs
 * Две карты одного достоинства и две карты другого достоинства.
 */
public class TwoPairs extends Combination {
    private final Set<Set<Card>> cardsInTwoPairs;

    public TwoPairs(Hand hand) throws IllegalCombinationException {
        super(hand);
        //Вычислить пару
        cardsInTwoPairs = Collections.unmodifiableSet(calc(hand.getCards()));
        if (cardsInTwoPairs.size() != 2) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    private Set<Set<Card>> calc(Set<Card> cards) {
        Collection<Set<Card>> pairs = CardsSetUtils.getAllPairs(cards);
        if (pairs.size() < 2) {
            return Collections.emptySet();
        }
        List<Set<Card>> list = new ArrayList<>(pairs);
        Comparator<Set<Card>> comparator = (set1, set2) -> {
            Card[] pair1Cards = set1.toArray(new Card[] {});
            Card[] pair2Cards = set2.toArray(new Card[] {});
            Card p1c1 = pair1Cards[0], p1c2 = pair1Cards[1];
            Card p2c1 = pair2Cards[0], p2c2 = pair2Cards[1];
            int result = p1c1.getRank().compareTo(p2c1.getRank());
            return result;
        };
        comparator = comparator.reversed();
        list.sort(comparator);

        Set<Set<Card>> result = new HashSet<>();
        result.add(list.get(0));
        result.add(list.get(1));
        return result;
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.two_pairs;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        return 0;
    }

    @Override
    protected SortedSet<Card> exactCards() {
        SortedSet<Card> result = SortedCardsSet.createSortedRankAndThanSortedSuit();
        for (Set<Card> innerSet: this.cardsInTwoPairs) {
            result.addAll(innerSet);
        }
        return result;
    }
}

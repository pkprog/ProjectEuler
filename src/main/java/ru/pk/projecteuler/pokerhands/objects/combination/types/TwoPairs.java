package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.IllegalCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
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
        this.cardsInTwoPairs = Collections.unmodifiableSet(calc(hand.getCards()));
        if (this.cardsInTwoPairs.size() != 2) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    private Set<Set<Card>> calc(Set<Card> cards) {
        Collection<Set<Card>> pairs = CardsSetUtils.getAllPairs(cards);
        if (pairs.size() < 2) {
            return Collections.emptySet();
        }
        List<Set<Card>> list = new ArrayList<>(pairs);
        list.sort(pairsSortComparator);

        Set<Set<Card>> result = new HashSet<>();
        result.add(list.get(0));
        result.add(list.get(1));
        return result;
    }

    public Set<Set<Card>> getCardsInTwoPairs() {
        return cardsInTwoPairs;
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.two_pairs;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof TwoPairs) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        TwoPairs that = (TwoPairs) o;

        List<Set<Card>> listThis = new ArrayList<>(this.cardsInTwoPairs);
        List<Set<Card>> listThat = new ArrayList<>(that.getCardsInTwoPairs());
        listThis.sort(pairsSortComparator);
        listThat.sort(pairsSortComparator);

        Iterator<Set<Card>> itThis = listThis.iterator();
        Iterator<Set<Card>> itThat = listThat.iterator();
        while (itThis.hasNext()) {
            Card[] cardsThis = itThis.next().toArray(new Card[] {});
            Card[] cardsThat = itThat.next().toArray(new Card[] {});
            //
            Card c1This = cardsThis[0];
            Card c1That = cardsThat[0];
            int tempResult = c1This.getRank().compare(c1That.getRank());
            if (tempResult != 0) {
                return tempResult;
            }
        }

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

    private static final Comparator<Set<Card>> pairsSortComparator;
    static {
        Comparator<Set<Card>> tempComparator = (set1, set2) -> {
            Card[] pair1Cards = set1.toArray(new Card[] {});
            Card[] pair2Cards = set2.toArray(new Card[] {});
            Card p1c1 = pair1Cards[0], p1c2 = pair1Cards[1];
            Card p2c1 = pair2Cards[0], p2c2 = pair2Cards[1];
            int result = p1c1.getRank().compareTo(p2c1.getRank());
            return result;
        };
        pairsSortComparator = tempComparator.reversed();
    }

}

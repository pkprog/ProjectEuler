package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.IllegalCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/**
 * Full House: Three of a kind and a pair.
 * Фулл-Хаус – три карты одного номинала и еще две – другого
 */
public class FullHouse extends Combination {
    private Set<Card> three;
    private Set<Card> two;

    public FullHouse(Hand hand) throws IllegalCombinationException {
        super(hand);
        Map<Integer, Set<Card>> map = calc(hand.getCards());
        if (map.get(2).size() == 2 && map.get(3).size() == 3) {
            this.three = Collections.unmodifiableSet(map.get(3));
            this.two = Collections.unmodifiableSet(map.get(2));
        } else {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    private Map<Integer, Set<Card>> calc(Set<Card> cards) {
        Map<Integer, Set<Card>> result = new HashMap<>();
        result.put(3, new HashSet<>());
        result.put(2, new HashSet<>());

        Collection<Set<Card>> threeList = CardsSetUtils.getAllThree(cards);
        if (threeList.size() == 1) {
            Set<Card> three = threeList.iterator().next();
            result.get(3).addAll(three);

            Set<Card> cutCards = new HashSet<>(cards);
            cutCards.removeAll(three);

            Collection<Set<Card>> twoList = CardsSetUtils.getAllPairs(cutCards);
            if (twoList.size() == 1) {
                Set<Card> two = twoList.iterator().next();
                result.get(2).addAll(two);
            }
        }

        return result;
    }

    public Set<Card> getThree() {
        return three;
    }

    public Set<Card> getTwo() {
        return two;
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.full_house;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof FullHouse) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        Card c3This = this.getThree().iterator().next();
        Card c2This = this.getTwo().iterator().next();
        SortedCardsSet thisCards = SortedCardsSet.createSortedRank().addCard(c3This).addCard(c2This);

        FullHouse that = (FullHouse) o;
        Card c3That = that.getThree().iterator().next();
        Card c2That = that.getTwo().iterator().next();
        SortedCardsSet thatCards = SortedCardsSet.createSortedRank().addCard(c3That).addCard(c2That);

        Iterator<Card> itThis = thisCards.iterator();
        Iterator<Card> itThat = thatCards.iterator();
        while (itThis.hasNext()) {
            int res = itThis.next().getRank().compareTo(itThat.next().getRank());
            if (res != 0) {
                return res;
            }
        }

        return 0;
    }

    @Override
    protected SortedSet<Card> exactCards() {
        SortedCardsSet set = SortedCardsSet.createSortedRank().addCards(three).addCards(two);
        return set;
    }
}

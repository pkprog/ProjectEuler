package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.Rank;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.IllegalCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Straight: All cards are consecutive values
 * Стрит – подряд, но не одной масти
 */
public class Straight extends Combination {
    private final SortedSet<Card> cardsInStraight;

    public Straight(Hand hand) throws IllegalCombinationException {
        super(hand);
        //Проверить Стрит
        cardsInStraight = Collections.unmodifiableSortedSet(calc(hand.getCards()));
        if (cardsInStraight.size() == 0) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    //Здесь есть подмена карты: большого туза на маленького
    private SortedSet<Card> calc(Set<Card> cards) {
        if (!CardsSetUtils.checkStraight(hand.getCards())) {
            return new TreeSet<>(Collections.emptySet());
        }

        SortedSet<Card> sorted = SortedCardsSet.createSortedRank().addCards(cards);
        //Нужно ли уменьшить туза
        List<Card> aces = cards.stream().filter(c -> Rank.ace.equals(c.getRank())).collect(Collectors.toList());
        if (aces.size() > 0) {
            List<Card> kings = cards.stream().filter(c -> Rank.king.equals(c.getRank())).collect(Collectors.toList());
            if (kings.size() > 0) { //Туз здесь большой
                //ok
            } else {//иначе туз маленький
                sorted.remove(aces.get(0));
                sorted.add(new Card(Rank.one, aces.get(0).getSuit()));
            }
        }

        return sorted;
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

        return this.cardsInStraight.first().getRank().compareTo(that.getCardsInStraight().first().getRank());
    }

    @Override
    protected SortedSet<Card> exactCards() {
        return this.cardsInStraight;
    }
}

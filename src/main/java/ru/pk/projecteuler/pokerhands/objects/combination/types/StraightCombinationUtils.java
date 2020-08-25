package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Rank;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Утилита для комбинации Флэш и Стрит-Флэш
 */
public class StraightCombinationUtils {

    //Здесь есть подмена карты: большого туза на маленького
    public static SortedSet<Card> calc(Set<Card> cards) {
        boolean bigAce = CardsSetUtils.checkStraightBigAce(cards);
        boolean smallAce = CardsSetUtils.checkStraightSmallAce(cards);
        if (!bigAce && !smallAce) {
            return new TreeSet<>(Collections.emptySet());
        }

        SortedSet<Card> sorted = SortedCardsSet.createSortedRank().addCards(cards);
        //Нужно ли уменьшить туза
        if (smallAce) {
            List<Card> aces = cards.stream().filter(c -> Rank.ace.equals(c.getRank())).collect(Collectors.toList());
            sorted.remove(aces.get(0));
            sorted.add(new Card(Rank.one, aces.get(0).getSuit()));
        }

        return sorted;
    }

}

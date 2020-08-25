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
        if (!CardsSetUtils.checkStraight(cards)) {
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

}

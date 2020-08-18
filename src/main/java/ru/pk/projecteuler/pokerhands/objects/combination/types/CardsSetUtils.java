package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class CardsSetUtils {

    /**
     * Возвращает список пар. Карты и пары и карты в парах не повторяются
     */
    public static Collection<Set<Card>> getAllPairs(Set<Card> cards) {
        Collection<Set<Card>> result = new LinkedList<>();
        Collection<Card> usedCards = new LinkedList<>();
        for (Card c1: cards) {
            if (usedCards.contains(c1)) continue;

            for (Card c2: cards) {
                if (c1.equals(c2)) continue;
                if (usedCards.contains(c2)) continue;

                if (c1.getRank().equals(c2.getRank())) {
                    Set<Card> pair = new HashSet<>(2);
                    pair.addAll(Arrays.asList(c1, c2));
                    result.add(pair);
                    //
                    usedCards.addAll(pair);
                }
            }
        }

        return result;
    }
}

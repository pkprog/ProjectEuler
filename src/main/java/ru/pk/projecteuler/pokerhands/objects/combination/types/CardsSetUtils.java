package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Rank;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

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
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Возвращает тройки карт с одним значением.
     */
    public static Collection<Set<Card>> getAllThree(Set<Card> cards) {
        Collection<Set<Card>> result = new LinkedList<>();
        Collection<Card> usedCards = new LinkedList<>();

        Set<Card> tempThree = new HashSet<>();
        for (Card c1: cards) {
            if (usedCards.contains(c1)) continue;
            tempThree.add(c1);

            for (Card c2: cards) {
                if (c1.equals(c2)) continue;
                if (usedCards.contains(c2) || tempThree.contains(c2)) continue;

                if (c1.getRank().equals(c2.getRank())) {
                    tempThree.add(c2);
                    if (tempThree.size() == 3) {
                        Set<Card> three = new HashSet<>(3);
                        three.addAll(tempThree);
                        result.add(three);
                        //
                        usedCards.addAll(tempThree);
                        break;
                    }
                }
            }
            tempThree.clear();
        }

        return result;
    }

    /**
     * Проверить, что все карты составляют Стрит
     */
    public static boolean checkStraight(Set<Card> cards) {
        SortedCardsSet sorted = SortedCardsSet.createSortedRank();
        sorted.addAll(cards);

        boolean isStraight = true;

        Card prev = null;
        boolean firstAce = false;
        for (Card c: sorted) {
            if (prev == null) {
                if (Rank.ace.compareTo(c.getRank()) == 0) {
                    firstAce = true;
                }
            } else {
                if (c.getRank().getValue() + 1 == prev.getRank().getValue()) {
                    //ok
                } else {
                    if (Rank.two.equals(prev.getRank())) {
                        if (Rank.ace.equals(c.getRank()) && !firstAce) {
                            //ok
                        } else {
                            isStraight = false;
                            break;
                        }
                    } else {
                        isStraight = false;
                        break;
                    }
                }
            }
            prev = c;
        }

        return isStraight;
    }
}

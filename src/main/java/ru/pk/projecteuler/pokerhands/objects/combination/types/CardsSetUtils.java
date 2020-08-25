package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Rank;
import ru.pk.projecteuler.pokerhands.objects.Suit;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return getAllN(cards, 3);
    }

    /**
     * Возвращает четвёрки карт с одним значением.
     */
    public static Collection<Set<Card>> getAllFour(Set<Card> cards) {
        return getAllN(cards, 4);
    }

    public static Collection<Set<Card>> getAllN(Set<Card> cards, final int TARGET_COUNT) {
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
                    if (tempThree.size() == TARGET_COUNT) {
                        Set<Card> three = new HashSet<>(TARGET_COUNT);
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
     * Проверить, что все карты составляют Стрит с тузом в качестве 1
     */
    public static boolean checkStraightSmallAce(Set<Card> cards) {
        SortedCardsSet sorted = SortedCardsSet.createSortedRank().addCards(cards);

        boolean isStraight = true;
        boolean smallAce = false;

        //Нужно ли уменьшить туза
        List<Card> aces = cards.stream().filter(c -> Rank.ace.equals(c.getRank())).collect(Collectors.toList());
        if (aces.size() > 0) {
            List<Card> kings = cards.stream().filter(c -> Rank.king.equals(c.getRank())).collect(Collectors.toList());
            if (kings.size() > 0) { //Туз здесь большой
                //ok
            } else {//иначе туз маленький
                smallAce = true;
                sorted.remove(aces.get(0));
                sorted.add(new Card(Rank.one, aces.get(0).getSuit()));
            }
        }

        if (!smallAce) return false;

        Card prev = null;
        for (Card c: sorted) {
            if (prev == null) {
            } else {
                if (c.getRank().getValue() + 1 == prev.getRank().getValue()) {
                    //ok
                } else {
                    isStraight = false;
                }
            }
            prev = c;
        }

        return isStraight;
    }


    /**
     * Проверить, что все карты составляют Стрит с тузом в качестве туза
     */
    public static boolean checkStraightBigAce(Set<Card> cards) {
        SortedCardsSet sorted = SortedCardsSet.createSortedRank().addCards(cards);

        boolean isStraight = true;
        boolean bigAce = true;

        //Нужно ли уменьшить туза
        List<Card> aces = cards.stream().filter(c -> Rank.ace.equals(c.getRank())).collect(Collectors.toList());
        if (aces.size() > 0) {
            List<Card> kings = cards.stream().filter(c -> Rank.king.equals(c.getRank())).collect(Collectors.toList());
            if (kings.size() > 0) { //Туз здесь большой
                //ok
            } else {//иначе туз маленький
                bigAce = false;
                sorted.remove(aces.get(0));
                sorted.add(new Card(Rank.one, aces.get(0).getSuit()));
            }
        }

        if (!bigAce) return false;

        Card prev = null;
        for (Card c: sorted) {
            if (prev == null) {
            } else {
                if (c.getRank().getValue() + 1 == prev.getRank().getValue()) {
                    //ok
                } else {
                    isStraight = false;
                }
            }
            prev = c;
        }

        return isStraight;
    }

    /**
     * Проверить, что все карты одной масти
     */
    public static boolean checkFlush(Set<Card> cards) {
        boolean isFlush = true;
        Suit prevSuit = null;
        for (Card c: cards) {
            if (prevSuit == null) {
                prevSuit = c.getSuit();
            } else {
                if (prevSuit.equals(c.getSuit())) {
                    //ok
                } else {
                    isFlush = false;
                    break;
                }
            }
        }

        return isFlush;
    }

}

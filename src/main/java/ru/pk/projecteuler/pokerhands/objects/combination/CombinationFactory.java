package ru.pk.projecteuler.pokerhands.objects.combination;

import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.types.HighCard;
import ru.pk.projecteuler.pokerhands.objects.combination.types.Pair;

public class CombinationFactory {

    public static HighCard createHighCard(Hand hand) {
        return new HighCard(hand);
    }

    public static Pair createPair(Hand hand) {
        try {
            return new Pair(hand);
        } catch (IllegalCombinationException e) {
            return null;
        }
    }


}

package ru.pk.projecteuler.pokerhands.objects.combination;

import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.types.HighCard;
import ru.pk.projecteuler.pokerhands.objects.combination.types.Pair;
import ru.pk.projecteuler.pokerhands.objects.combination.types.Straight;
import ru.pk.projecteuler.pokerhands.objects.combination.types.ThreeOfAKind;
import ru.pk.projecteuler.pokerhands.objects.combination.types.TwoPairs;

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

    public static TwoPairs createTwoPairs(Hand hand) {
        try {
            return new TwoPairs(hand);
        } catch (IllegalCombinationException e) {
            return null;
        }
    }

    public static ThreeOfAKind createThreeOfAKind(Hand hand) {
        try {
            return new ThreeOfAKind(hand);
        } catch (IllegalCombinationException e) {
            return null;
        }
    }

    public static Straight createStraight(Hand hand) {
        try {
            return new Straight(hand);
        } catch (IllegalCombinationException e) {
            return null;
        }
    }

}

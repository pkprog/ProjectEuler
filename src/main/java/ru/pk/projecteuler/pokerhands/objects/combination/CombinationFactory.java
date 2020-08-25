package ru.pk.projecteuler.pokerhands.objects.combination;

import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.types.*;

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

    public static Flush createFlush(Hand hand) {
        try {
            return new Flush(hand);
        } catch (IllegalCombinationException e) {
            return null;
        }
    }

    public static FullHouse createFullHouse(Hand hand) {
        try {
            return new FullHouse(hand);
        } catch (IllegalCombinationException e) {
            return null;
        }
    }

    public static FourOfAKind createFourOfAKind(Hand hand) {
        try {
            return new FourOfAKind(hand);
        } catch (IllegalCombinationException e) {
            return null;
        }
    }

    public static StraightFlush createStraightFlush(Hand hand) {
        try {
            return new StraightFlush(hand);
        } catch (IllegalCombinationException e) {
            return null;
        }
    }

    public static RoyalFlush createRoyalFlush(Hand hand) {
        try {
            return new RoyalFlush(hand);
        } catch (IllegalCombinationException e) {
            return null;
        }
    }


}

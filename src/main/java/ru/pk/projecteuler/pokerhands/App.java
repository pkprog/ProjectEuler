package ru.pk.projecteuler.pokerhands;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.Rank;
import ru.pk.projecteuler.pokerhands.objects.Suit;

import java.util.Arrays;
import java.util.HashSet;

public class App {

    public static void main(String[] args) {
        Calc c = new Calc();
        System.out.println("result=" + c.process(getHand1(), getHand2()));
    }

    //5C AD 5D AC 9C
    private static Hand getHand1() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.five, Suit.clubs),
                new Card(Rank.ace, Suit.diamonds),
                new Card(Rank.five, Suit.diamonds),
                new Card(Rank.ace, Suit.clubs),
                new Card(Rank.nine, Suit.clubs)
        )));
    }

    //7C 2H 8D 2D 2S
    private static Hand getHand2() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.seven, Suit.clubs),
                new Card(Rank.two, Suit.hearts),
                new Card(Rank.eight, Suit.diamonds),
                new Card(Rank.two, Suit.diamonds),
                new Card(Rank.two, Suit.spades)
        )));
    }
}

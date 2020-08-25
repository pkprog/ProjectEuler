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

    //6C 6D 6H 6S TC
    private static Hand getHand1() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.six, Suit.clubs),
                new Card(Rank.six, Suit.diamonds),
                new Card(Rank.six, Suit.hearts),
                new Card(Rank.six, Suit.spades),
                new Card(Rank.ten, Suit.clubs)
        )));
    }

    //7C 7D 8D 8C 8H
    private static Hand getHand2() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.seven, Suit.clubs),
                new Card(Rank.seven, Suit.diamonds),
                new Card(Rank.eight, Suit.diamonds),
                new Card(Rank.eight, Suit.clubs),
                new Card(Rank.eight, Suit.hearts)
        )));
    }
}

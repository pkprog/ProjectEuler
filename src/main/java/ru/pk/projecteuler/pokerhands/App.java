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

    //7C 8C 9C JC TC
    private static Hand getHand1() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.seven, Suit.clubs),
                new Card(Rank.eight, Suit.clubs),
                new Card(Rank.nine, Suit.clubs),
                new Card(Rank.jack, Suit.clubs),
                new Card(Rank.ten, Suit.clubs)
        )));
    }

    //2D 3D 4D 5D AD
    private static Hand getHand2() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.two, Suit.diamonds),
                new Card(Rank.three, Suit.diamonds),
                new Card(Rank.four, Suit.diamonds),
                new Card(Rank.five, Suit.diamonds),
                new Card(Rank.ace, Suit.diamonds)
        )));
    }
}

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

    //AC KD QD JC TC
    private static Hand getHand1() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.ace, Suit.clubs),
                new Card(Rank.king, Suit.diamonds),
                new Card(Rank.queen, Suit.diamonds),
                new Card(Rank.jack, Suit.clubs),
                new Card(Rank.ten, Suit.clubs)
        )));
    }

    //7C QC 8C 3C 2C
    private static Hand getHand2() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.seven, Suit.clubs),
                new Card(Rank.queen, Suit.clubs),
                new Card(Rank.eight, Suit.clubs),
                new Card(Rank.three, Suit.clubs),
                new Card(Rank.two, Suit.clubs)
        )));
    }
}

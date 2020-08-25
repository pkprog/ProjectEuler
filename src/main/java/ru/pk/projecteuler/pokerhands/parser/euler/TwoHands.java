package ru.pk.projecteuler.pokerhands.parser.euler;

import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.parser.ParsedRow;

public class TwoHands extends ParsedRow {
    private Hand hand1;
    private Hand hand2;

    public TwoHands(Hand hand1, Hand hand2) {
        this.hand1 = hand1;
        this.hand2 = hand2;
    }

    public Hand getHand1() {
        return hand1;
    }

    public Hand getHand2() {
        return hand2;
    }
}

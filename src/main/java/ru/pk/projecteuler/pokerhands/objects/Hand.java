package ru.pk.projecteuler.pokerhands.objects;

import java.util.Collections;
import java.util.Set;

public class Hand {
    private Set<Card> cards;

    public Hand(Set<Card> cards) {
        assert cards != null && cards.size() == 5;
        this.cards = Collections.unmodifiableSet(cards);
    }

    public Set<Card> getCards() {
        return cards;
    }
}

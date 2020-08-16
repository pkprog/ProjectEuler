package ru.pk.projecteuler.pokerhands.objects;

import java.util.Collections;
import java.util.Set;

public class Hand {
    public static final int MAX_CARDS_IN_HAND = 5;

    private final Set<Card> cards;

    public Hand(Set<Card> cards) {
        if (cards == null || cards.size() != MAX_CARDS_IN_HAND) {
            throw new IllegalArgumentException("Число карт неверное (<> "+ MAX_CARDS_IN_HAND +")");
        }
        this.cards = Collections.unmodifiableSet(cards);
    }

    public Set<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hand hand = (Hand) o;
        int i = cards.size();
        for (Card c: cards) {
            for (Card cThat: hand.getCards()) {
                if (c.equals(cThat)) {
                    i--;
                    break;
                }
            }
        }

        return i == 0;
    }

    @Override
    public int hashCode() {
        return getCards().hashCode();
    }
}

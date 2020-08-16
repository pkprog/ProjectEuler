package ru.pk.projecteuler.pokerhands.objects;

public class Card {
    private Rank rank; //Номер
    private Suit suit; //Масть

    public Card(Rank rank, Suit suit) {
        assert rank != null && suit != null;
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rank != card.rank) return false;
        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = rank.hashCode();
        result = 31 * result + suit.hashCode();
        return result;
    }
}

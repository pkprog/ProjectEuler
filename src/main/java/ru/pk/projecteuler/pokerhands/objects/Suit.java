package ru.pk.projecteuler.pokerhands.objects;

import java.util.StringJoiner;

/**
 * Масть
 */
public enum Suit {
    diamonds("D", "бубны"),
    hearts("H", "червы"),
    spades("S", "пики"),
    clubs("C", "крести"); //(трефы)

    private String shortName;
    private String dsc;

    Suit(String shortName, String dsc) {
        this.shortName = shortName;
        this.dsc = dsc;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDsc() {
        return dsc;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Suit.class.getSimpleName() + "[", "]")
                .add("dsc='" + dsc + "'")
                .toString();
    }
}

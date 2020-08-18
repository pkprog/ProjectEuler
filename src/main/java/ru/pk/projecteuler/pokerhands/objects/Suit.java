package ru.pk.projecteuler.pokerhands.objects;

import java.util.StringJoiner;

/**
 * Масть
 */
public enum Suit {
    diamonds("D", "б", "бубны"),
    hearts("H", "ч", "червы"),
    spades("S", "п", "пики"),
    clubs("C", "к", "крести"); //(трефы)

    private String shortName;
    private String shortNameRu;
    private String dsc;

    Suit(String shortName, String shortNameRu, String dsc) {
        this.shortName = shortName;
        this.shortNameRu = shortNameRu;
        this.dsc = dsc;
    }

    public String getShortName() {
        return shortName;
    }

    public String getShortNameRu() {
        return shortNameRu;
    }

    public String getDsc() {
        return dsc;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "Масть" + "[", "]")
                .add(dsc)
                .toString();
    }
}

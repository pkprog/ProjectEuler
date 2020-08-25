package ru.pk.projecteuler.pokerhands.objects;

import java.util.StringJoiner;

public enum Rank {
    one(1, "a", "т", "туз"),
    two(2, "2", "2", "2"),
    three(3, "3", "3", "3"),
    four(4, "4", "4", "4"),
    five(5, "5", "5", "5"),
    six(6, "6", "6", "6"),
    seven(7, "7", "7", "7"),
    eight(8, "8", "8", "8"),
    nine(9, "9", "9", "9"),
    ten(10, "T", "10", "10"),
    jack(11, "J", "В", "Валет"),
    queen(12, "Q", "Д", "Дама"),
    king(13, "K", "К", "Король"),
    ace(14, "A", "Т", "Туз");

    private byte value;
    private String shortName;
    private String shortNameRu;
    private String dsc;

    Rank(int value, String shortName, String shortNameRu, String dsc) {
        this.value = (byte) value;
        this.shortName = shortName;
        this.shortNameRu = shortNameRu;
        this.dsc = dsc;
    }

    public byte getValue() {
        return value;
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

    public int compare(Rank other) {
        return Byte.compare(this.getValue(), other.getValue());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "Ранк" + "[", "]")
                .add(dsc)
                .toString();
    }
}

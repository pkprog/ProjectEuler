package ru.pk.projecteuler.pokerhands.objects;

public enum Rank {
    two(2, "2", "2"),
    three(3, "3", "3"),
    four(4, "4", "4"),
    five(5, "5", "5"),
    six(6, "6", "6"),
    seven(7, "7", "7"),
    eight(8, "8", "8"),
    nine(9, "9", "9"),
    ten(10, "10", "10"),
    jack(11, "J", "Валет"),
    queen(12, "Q", "Дама"),
    king(13, "K", "Король"),
    ace(14, "A", "Туз");

    private byte value;
    private String shortName;
    private String dsc;

    Rank(int value, String shortName, String dsc) {
        this.value = (byte) value;
        this.shortName = shortName;
        this.dsc = dsc;
    }

    public byte getValue() {
        return value;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDsc() {
        return dsc;
    }
}

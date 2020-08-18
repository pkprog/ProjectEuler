package ru.pk.projecteuler.pokerhands.objects.combination;

public enum CombinationRank {
    high_card(0, "Highest value card", "Старшая Карта"),
    one_pair(1, "Two cards of the same value", "Пара"),
    two_pairs(2, "Two different pairs", "Две Пары"),
    three_of_a_kind(3, "Three cards of the same value", "Тройка (Трипс, Сет) - три карты одного номинала"),
    straight(4, "All cards are consecutive values", "Стрит – подряд, но не одной масти"),
    flush(5, "All cards of the same suit", "Флеш – все одной масти, но вразброс"),
    full_house(6, "Three of a kind and a pair", "Фулл-Хаус – три карты одного номинала и еще две – другого"),
    four_of_a_kind(7, "Four cards of the same value", "Каре – четыре одинаковые"),
    straight_flush(8, "All cards are consecutive values of same suit", "Стрит-Флеш – все в масть по порядку"),
    royal_flush(9, "Ten, Jack, Queen, King, Ace, in same suit", "Роял-Флеш – все карты одной масти, они идут по порядку от десятки до туза");

    private final byte value;
    private final String dsc;
    private final String dscRu;

    CombinationRank(int value, String dsc, String dscRu) {
        this.value = (byte) value;
        this.dsc = dsc;
        this.dscRu = dscRu;
    }

    public byte getValue() {
        return value;
    }

    public String getDsc() {
        return dsc;
    }

    public String getDscRu() {
        return dscRu;
    }
}

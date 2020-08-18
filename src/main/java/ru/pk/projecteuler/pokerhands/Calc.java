package ru.pk.projecteuler.pokerhands;

import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationSelector;

public class Calc {

    public long process(Hand player1, Hand player2) {
        long result = 0;
        CombinationSelector selector = new CombinationSelector();
        Combination combination1 = selector.getHighest(player1);
        Combination combination2 = selector.getHighest(player2);

        return combination1.compareToInform(combination2);
    }

}

package ru.pk.projecteuler.pokerhands.objects.combination;

import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.types.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSelector {
    /**
     * Максимальная комбинация
     */
    public Combination getHighest(Hand hand) {
        List<Combination> all = new ArrayList<>(getAll(hand));
        if (all.size() > 0) {
            all.sort(Combination::compareTo);
            return all.get(all.size() - 1);
        } else {
            throw new IllegalArgumentException("Не найдена ни одна комбинация. Что странно");
        }
    }

    /**
     * Все комбинации
     */
    public Set<Combination> getAll(Hand hand) {
        Set<Combination> result = new HashSet<>();
        result.add(CombinationFactory.createHighCard(hand));
        Pair pair = CombinationFactory.createPair(hand);
        if (pair != null) {
            result.add(pair);
        }

        return result;
    }
}

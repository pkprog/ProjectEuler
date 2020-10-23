package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.IllegalCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.Iterator;
import java.util.SortedSet;

/**
 * Flush: All cards of the same suit.
 * Флеш – все одной масти, но вразброс
 */
public class Flush extends Combination {
    public Flush(Hand hand) throws IllegalCombinationException {
        super(hand);
        if (CardsSetUtils.checkFlush(hand.getCards())) {
            //ok
        } else {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.flush;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof Flush) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        Flush that = (Flush) o;
        SortedSet<Card> sortedThis = SortedCardsSet.createSortedRank().addCards(this.getCards());
        SortedSet<Card> sortedThat = SortedCardsSet.createSortedRank().addCards(that.getCards());

        Iterator<Card> itThis = sortedThis.iterator();
        Iterator<Card> itThat = sortedThat.iterator();
        while (itThis.hasNext()) {
            int res = itThis.next().getRank().compare(itThat.next().getRank());
            if (res != 0) {
                return res;
            }
        }

        return 0;
    }

    @Override
    protected SortedSet<Card> exactCards() {
        SortedCardsSet set = SortedCardsSet.createSortedRank();
        set.addAll(this.getCards());
        return set;
    }
}

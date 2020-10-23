package ru.pk.projecteuler.pokerhands.objects.combination.types;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.combination.Combination;
import ru.pk.projecteuler.pokerhands.objects.combination.CombinationRank;
import ru.pk.projecteuler.pokerhands.objects.combination.CompareCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.IllegalCombinationException;
import ru.pk.projecteuler.pokerhands.objects.combination.SortedCardsSet;

import java.util.Set;
import java.util.SortedSet;

/**
 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 * Роял-Флеш – все карты одной масти, они идут по порядку от десятки до туза
 */
public class RoyalFlush extends Combination {

    public RoyalFlush(Hand hand) throws IllegalCombinationException {
        super(hand);
        if (!calc(hand.getCards())) {
            throw new IllegalCombinationException("Комбинация не найдена", this.getClass().getSimpleName());
        }
    }

    private boolean calc(Set<Card> cards) {
        boolean bigAce = CardsSetUtils.checkStraightSmallAce(cards);
        boolean flush = CardsSetUtils.checkFlush(cards);
        return bigAce && flush;
    }

    @Override
    public CombinationRank combinationRank() {
        return CombinationRank.royal_flush;
    }

    @Override
    protected int compareCardsTo(Combination o) {
        if (o instanceof RoyalFlush) {
        } else {
            throw new CompareCombinationException("Различаются сравниваемые комбинации", this.getClass().getSimpleName());
        }

        RoyalFlush that = (RoyalFlush) o;

        SortedSet<Card> thisCards = SortedCardsSet.createSortedRank().addCards(this.getCards());
        SortedSet<Card> thatCards = SortedCardsSet.createSortedRank().addCards(that.getCards());

        //Кажется, тут всегда равенство, но так красивее.
        return thisCards.first().getRank().compare(thatCards.first().getRank());
    }

    @Override
    protected SortedSet<Card> exactCards() {
        return SortedCardsSet.createSortedRank().addCards(this.getCards());
    }
}

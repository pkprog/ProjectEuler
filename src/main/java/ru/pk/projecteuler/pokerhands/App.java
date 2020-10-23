package ru.pk.projecteuler.pokerhands;

import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.Rank;
import ru.pk.projecteuler.pokerhands.objects.Suit;
import ru.pk.projecteuler.pokerhands.objects.combination.NotFoundCombinationsException;
import ru.pk.projecteuler.pokerhands.parser.ByRowParser;
import ru.pk.projecteuler.pokerhands.parser.ParsedRow;
import ru.pk.projecteuler.pokerhands.parser.euler.NoRowsException;
import ru.pk.projecteuler.pokerhands.parser.euler.PrEulerFileParser;
import ru.pk.projecteuler.pokerhands.parser.euler.TwoHands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        Calc c = new Calc();

        int resultCount = 0;

        try {
            ByRowParser parser = getParser();
            while (true) {
                try {
                    Optional<ParsedRow> parsedRowOpt = parser.parseNext();
                    if (parsedRowOpt.isPresent()) {
                        if (parsedRowOpt.get() instanceof TwoHands) {
                            TwoHands twoHands = (TwoHands) parsedRowOpt.get();
                            if (twoHands.getHand1() != null && twoHands.getHand2() != null) {
                                if (c.process(twoHands.getHand1(), twoHands.getHand2()) > 0) {
                                    resultCount++;
                                }
                            }
                        }
                    }
                } catch (NoRowsException e) {
                    System.out.println("EonOfFile:" + e.getMessage());
                    break;
                } catch (NotFoundCombinationsException e) {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Total result of player 1 wins: " + resultCount);
//        System.out.println("result=" + c.process(getHand1(), getHand2()));
    }

    private static ByRowParser getParser() throws FileNotFoundException {
        final String PATH = "src\\main\\resources\\pokerhand\\p054_poker.txt";
        return PrEulerFileParser.createParser(PATH);
    }

    //TC QC JC KC TC
    private static Hand getHand1() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.ten, Suit.clubs),
                new Card(Rank.queen, Suit.clubs),
                new Card(Rank.jack, Suit.clubs),
                new Card(Rank.king, Suit.clubs),
                new Card(Rank.ace, Suit.clubs)
        )));
    }

    //2D 3D 4D 5D AD
    private static Hand getHand2() {
        return new Hand(new HashSet<>(Arrays.asList(
                new Card(Rank.two, Suit.diamonds),
                new Card(Rank.three, Suit.diamonds),
                new Card(Rank.four, Suit.diamonds),
                new Card(Rank.five, Suit.diamonds),
                new Card(Rank.ace, Suit.diamonds)
        )));
    }
}

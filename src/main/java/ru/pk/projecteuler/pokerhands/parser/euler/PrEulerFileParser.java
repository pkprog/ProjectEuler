package ru.pk.projecteuler.pokerhands.parser.euler;

import ru.pk.projecteuler.lib.StringUtils;
import ru.pk.projecteuler.pokerhands.objects.Card;
import ru.pk.projecteuler.pokerhands.objects.Hand;
import ru.pk.projecteuler.pokerhands.objects.Rank;
import ru.pk.projecteuler.pokerhands.objects.Suit;
import ru.pk.projecteuler.pokerhands.parser.ByRowParser;
import ru.pk.projecteuler.pokerhands.parser.ParsedRow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PrEulerFileParser implements ByRowParser {
    private final File file;
    private final BufferedReader reader;
    private long readeLineNumber;

    private PrEulerFileParser(File file, FileReader reader) {
        this.file = file;
        this.reader = new BufferedReader(reader);
        this.readeLineNumber = 0;
    }

    public static PrEulerFileParser createParser(String path) throws FileNotFoundException {
        File f = new File(path);
        if (!f.exists()) {
            throw new FileNotFoundException("File not found");
        }
        return new PrEulerFileParser(f, new FileReader(f));
    }

    @Override
    public Optional<ParsedRow> parseNext() throws IOException, NoRowsException {
        String line = reader.readLine();
        if (line == null) {
            throw new NoRowsException("lines=" + readeLineNumber);
        }
        readeLineNumber++;
        if (StringUtils.isEmpty(line))
            return Optional.empty();
        return Optional.ofNullable(parse(line));
    }

    private ParsedRow parse(String text) {
        String[] parsed = StringUtils.split(text, " ");
        if (parsed.length == 10) {
            //Карты первого игрока
            Set<Card> cards1 = new HashSet<>();
            for (int i = 0; i < 5; i++) {
                Card c = parseFromRankSuitStr(parsed[i]);
                if (c != null) {
                    cards1.add(c);
                }
            }
            //Карты второго игрока
            Set<Card> cards2 = new HashSet<>();
            for (int i = 5; i < 10; i++) {
                Card c = parseFromRankSuitStr(parsed[i]);
                if (c != null) {
                    cards2.add(c);
                }
            }

            if (cards1.size() == Hand.MAX_CARDS_IN_HAND && cards2.size() == Hand.MAX_CARDS_IN_HAND) {
                TwoHands twoHands = new TwoHands(
                        new Hand(cards1),
                        new Hand(cards2)
                );
                return twoHands;
            }
        }
        return null;
    }

    private Card parseFromRankSuitStr(String rs) {
        if (StringUtils.clearString(rs).length() != 2) {
            return null;
        }
        Suit suit = null;
        Rank rank = null;
        String rankStr = StringUtils.character(StringUtils.clearString(rs), 1);
        String suitStr = StringUtils.character(StringUtils.clearString(rs), 2);
        switch (rankStr) {
            case "2": rank = Rank.two; break;
            case "3": rank = Rank.three; break;
            case "4": rank = Rank.four; break;
            case "5": rank = Rank.five; break;
            case "6": rank = Rank.six; break;
            case "7": rank = Rank.seven; break;
            case "8": rank = Rank.eight; break;
            case "9": rank = Rank.nine; break;
            case "T": rank = Rank.ten; break;
            case "J": rank = Rank.jack; break;
            case "Q": rank = Rank.queen; break;
            case "K": rank = Rank.king; break;
            case "A": rank = Rank.ace; break;
        }
        switch (suitStr) {
            case "H": suit = Suit.hearts; break;
            case "C": suit = Suit.clubs; break;
            case "S": suit = Suit.spades; break;
            case "D": suit = Suit.diamonds; break;
        }

        if (suit == null || rank == null) return null;
        return new Card(rank, suit);
    }
}

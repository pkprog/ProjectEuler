package ru.pk.projecteuler.pokerhands.parser.euler;

import ru.pk.projecteuler.pokerhands.parser.ByRowParser;
import ru.pk.projecteuler.pokerhands.parser.ParsedRow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class PrEulerFileParser implements ByRowParser {
    private final File file;
    private final BufferedReader reader;

    private PrEulerFileParser(File file, FileReader reader) {
        this.file = file;
        this.reader = new BufferedReader(reader);
    }

    public static PrEulerFileParser createParser(String path) throws FileNotFoundException {
        File f = new File(path);
        if (!f.exists()) {
            throw new FileNotFoundException("File not found");
        }
        return new PrEulerFileParser(f, new FileReader(f));
    }

    @Override
    public Optional<ParsedRow> parseNext() throws IOException {
        String line = reader.readLine();
        if (line == null)
            return Optional.empty();




        return Optional.empty();
    }

    private ParsedRow parse(String text) {

    }
}

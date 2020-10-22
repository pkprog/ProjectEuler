package ru.pk.projecteuler.pokerhands.parser;

import java.io.IOException;
import java.util.Optional;

public interface ByRowParser {
    /**
     * Если строк больше нет, то Optional = false
     */
    Optional<ParsedRow> parseNext() throws IOException;
}

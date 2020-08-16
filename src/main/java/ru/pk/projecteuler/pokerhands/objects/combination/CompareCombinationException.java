package ru.pk.projecteuler.pokerhands.objects.combination;

public class CompareCombinationException extends RuntimeException {
    public CompareCombinationException(String message, String combinationName) {
        super(message + ": " + combinationName);
    }
}

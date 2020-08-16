package ru.pk.projecteuler.pokerhands.objects.combination;

public class IllegalCombinationException extends Exception {
    public IllegalCombinationException(String message, String combinationName) {
        super(message + ": " + combinationName);
    }
}

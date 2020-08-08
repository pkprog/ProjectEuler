package ru.pk.projecteuler.lib;

import java.util.Arrays;

public class DigitsEquals {

    public static boolean equals(byte[] array1, byte[] array2) {
        byte[] array11 = Arrays.copyOf(array1, array1.length);
        byte[] array22 = Arrays.copyOf(array2, array2.length);
        Arrays.sort(array11);
        Arrays.sort(array22);
        return Arrays.equals(array11, array22);
    }

}

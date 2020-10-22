package ru.pk.projecteuler.lib;

import java.util.Arrays;

public class StringUtils {

    public static boolean isEmpty(String value) {
        return value == null || value.equals("") || value.trim().equals("");
    }
    public static boolean notEmpty(String value) {
        return !isEmpty(value);
    }
    public static String clearString(String value) {
        return value == null ? "" : value.trim();
    }
    /**
     * Один симвод из строки. Нумерация с 1
     */
    public static String character(String value, int number) {
        if (value.length() < number) throw new IndexOutOfBoundsException("value.length < number");
        return value.substring(number-1, number);
    }
    /**
     * Подсчет вхождений подстроки
     * @param value Строка
     * @param occur Подстрока
     * @return число вхождений
     */
    public static int occurences(String value, String occur) {
        if (isEmpty(value) || occur == null || occur.length() > value.length()) return 0;
        int i = 0;
        int prevIndex = 0;
        while (true) {
            int index = value.indexOf(occur, prevIndex);
            if (index < 0) {
                break;
            } else {
                i++;
                prevIndex = index+1;
            }
        }
        return i;
    }

    public static String[] split(String value, String delimiter) {
        if (isEmpty(value)) return new String[0];
        String[] result = new String[occurences(value, delimiter) + 1];
        String subValue = value;
        int i = 0;
        while (true) {
            int index = subValue.indexOf(delimiter);
            if (index < 0) {
                if (notEmpty(subValue)) {
                    result[i] = subValue;
                    i++;
                }
                break;
            } else {
                String f = subValue.substring(0, index);
                String s = subValue.substring(index + 1);
                if (notEmpty(f)) {
                    result[i] = f;
                    i++;
                }
                subValue = s;
            }
        }

        if (i < result.length) {
            return Arrays.copyOf(result, i);
        }
        return result;
    }

}

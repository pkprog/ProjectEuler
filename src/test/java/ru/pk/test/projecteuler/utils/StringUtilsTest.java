package ru.pk.test.projecteuler.utils;

import org.junit.Test;
import ru.pk.projecteuler.lib.StringUtils;
import ru.pk.projecteuler.log.ConsoleLogger;

public class StringUtilsTest {

    @Test
    public void testSplit() {
        ConsoleLogger.log(StringUtils.split(" jghjjhg1 2      ", " "));
    }

    @Test
    public void testOccurences() {
        ConsoleLogger.log(StringUtils.occurences(" 1 2  ghjjgjhg sdf", " "));
    }

    @Test
    public void testOneCharacter() {
        ConsoleLogger.log(StringUtils.character("ty", 1));
        ConsoleLogger.log(StringUtils.character("ty", 2));
    }
}

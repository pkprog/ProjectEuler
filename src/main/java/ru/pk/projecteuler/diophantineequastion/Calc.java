package ru.pk.projecteuler.diophantineequastion;

import ru.pk.projecteuler.log.ConsoleLogger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Calc {
    private static final long MAX_VALUE_Y = (long) Math.sqrt(Long.MAX_VALUE)/1000;

    /**
     * x^2 – Dy^2 = 1
     * x = SQRT(1 + Dy^2)
     */
    public long process(final int maxD) {
        List<Long> valuesX = new ArrayList<>(100);

        for (int D = 1; D <= maxD; D++) {
            for (long valueY = 1; valueY < Long.MAX_VALUE; valueY++) {
                if (valueY > MAX_VALUE_Y) break;

                long valueZ = 1 + D * valueY*valueY;
//                double d = Math.sqrt(valueZ);

                //Check value
                boolean foundX = false;
                for (long valueX = valueY; valueX < (valueY * D + 1); valueX++) {
                    long valueXsqr = valueX * valueX;
                    if (valueXsqr == valueZ) {
                        valuesX.add(valueX);
                        foundX = true;
                        break;
                    }
                }

//                for (Long testValueX: sqrts) {
//                    if (testValueX == valueZ) {
//                        valuesX.add(testValueX);
//                        foundX = true;
//                        break;
//                    }
//                }
                if (foundX) {
                    break;
                }

                //Check value
/*
                long tempIntegerPart = (long) d;
                double remainder = d - tempIntegerPart;
                if (remainder < 0.0000001d) {
                    long valueX = (long) Math.floor(d);
                    valuesX.add(valueX);
                }
*/
            }
        }
        ConsoleLogger.log("Found values X count=", String.valueOf(valuesX.size()));
        valuesX.sort(Comparator.comparing((Long m) -> m.longValue()).reversed());
        return valuesX.size() > 0 ? valuesX.iterator().next() : 0;
    }

}

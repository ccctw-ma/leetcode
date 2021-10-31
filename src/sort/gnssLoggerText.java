package sort;

import java.math.BigDecimal;

/**
 * @author msc
 * @version 1.0
 * @date 2021/9/15 15:55
 */
public class gnssLoggerText {


    public static void cal() {
        double ReceivedSvTimeNanos = 186854377410807.0;
        double TimeOffsetNanos = 0.0;
        double TimeNanos = 98597060000000.0;
        double FullBiasNanos = -1.315528257385472798 * 1e18d;
        double BiasNanos = -0.42666843408203125;
        final long NUMBER_SECONDS_PER_WEEK = 604800L;
        final long NUMBER_NANOS_PER_WEEK = 604800000000000L;
        double gpsTime = TimeNanos - (FullBiasNanos + BiasNanos);
        double SPEED_OF_LIGHT = 299792458.0;
        double tRxGPS =
                (gpsTime + TimeOffsetNanos) * 1e-9;
        double weekNumberSeconds = Math.floor((-1. * FullBiasNanos * 1e-9) / NUMBER_SECONDS_PER_WEEK)
                * NUMBER_SECONDS_PER_WEEK;
        double timeSecond = tRxGPS - weekNumberSeconds - ReceivedSvTimeNanos * 1e-9;
        double pseudorange = timeSecond
                * SPEED_OF_LIGHT;
        System.out.println(pseudorange);
        double tRxGPS2 =
                gpsTime + TimeOffsetNanos;
        double weekNumberNanos = Math.floor((-1. * FullBiasNanos) / NUMBER_NANOS_PER_WEEK)
                * NUMBER_NANOS_PER_WEEK;
        double time2 = (tRxGPS2 - weekNumberNanos - ReceivedSvTimeNanos) / 1e9;
        double pseudorange2 =
               time2 * SPEED_OF_LIGHT;
        System.out.println(pseudorange2);
// 1.315626854445473E9
// 1.31544E9
// 186854.4454729557
// 1.31562685444547277E18
// 1.86854445472768E14
        BigDecimal receiveTime = new BigDecimal(String.valueOf(ReceivedSvTimeNanos));
        BigDecimal timeNanos = new BigDecimal(String.valueOf(TimeNanos));
        BigDecimal fullBiasNanos = new BigDecimal(String.valueOf(FullBiasNanos));
        BigDecimal biasNanos = new BigDecimal(String.valueOf(BiasNanos));
        BigDecimal nanos_week = new BigDecimal(NUMBER_NANOS_PER_WEEK);
        BigDecimal light = new BigDecimal(String.valueOf(SPEED_OF_LIGHT));
        BigDecimal tr = timeNanos.subtract(fullBiasNanos.add(biasNanos));
        BigDecimal weekNumber = new BigDecimal(String.valueOf(weekNumberNanos));
        BigDecimal time3 = tr.subtract(weekNumber).subtract(receiveTime);
        BigDecimal range = time3.divide(new BigDecimal("1e9")).multiply(light);
        System.out.println(range);
    }

    public static void calc2() {
        double ReceivedSvTimeNanos = 186854377410807.0;
        double TimeOffsetNanos = 0.0;
        double TimeNanos = 98597060000000.0;
        double FullBiasNanos = -1.315528257385472798 * 1e18;
        double BiasNanos = -0.46668243408203125;
        final long NUMBER_NANO_SECONDS_PER_WEEK = 604800000000000L;
        final long NUMBER_SECONDS_PER_WEEK = 604800L;
        double gpsTime = TimeNanos - (FullBiasNanos + BiasNanos);
        double SPEED_OF_LIGHT = 299792458.0;
        double tRxGPS =
                gpsTime + TimeOffsetNanos;
        double weekNumberNanos = Math.floor((-1. * FullBiasNanos) / NUMBER_NANO_SECONDS_PER_WEEK)
                * NUMBER_NANO_SECONDS_PER_WEEK;
        double pseudorange = (tRxGPS - weekNumberNanos - ReceivedSvTimeNanos)
                * SPEED_OF_LIGHT;
        System.out.println(pseudorange);
    }


    public static void main(String[] args) {
        cal();
        System.out.println(4.015 * 100);
        System.out.println(1.0 / 3);
        System.out.println(Float.NEGATIVE_INFINITY);
        float a = 123.45678901234567890f;
        double b = 123.45678901234567890d;
        System.out.println(a);
        System.out.println(b);
        System.out.println(2.24d);
        System.out.println(Double.toHexString(-1.315528257385472798 * 1e18));
        System.out.println(Double.toHexString(-0.42666843408203125d));
        System.out.println(Double.toHexString(-4.2666843408203125*1e-1));
        System.out.println(-4.2666843408203125*1e-1);
        System.out.println(-0.42666843408203125d);
        System.out.println(Double.toHexString(186854377410807.0));

    }
}

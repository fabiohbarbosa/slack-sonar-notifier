package com.wordpress.fabiohbarbosa.notifier.sonar.provider.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeriodFixture {

    public static List<Period> newPeriods() {
        List<Period> periods = new ArrayList<>();
        periods.add(newPeriod(1));
        periods.add(newPeriod(2));
        periods.add(newPeriod(3));
        return periods;
    }

    public static Period newPeriod() {
        return newPeriod(1);
    }

    public static Period newPeriod(Integer index) {
        Period period = new Period();
        period.setIndex(index);
        period.setDate(new Date());
        period.setMode("days");
        period.setParameter("30");
        return period;
    }
}
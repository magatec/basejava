package com.urise.webapp.util;

import com.urise.webapp.model.Period;

public class HtmlUtil {
    public static String formatDates(Period period) {
        return DateUtil.format(period.getStart()) + " - " + DateUtil.format(period.getEnd());
    }
}

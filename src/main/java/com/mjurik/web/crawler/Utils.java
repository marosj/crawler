package com.mjurik.web.crawler;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Marian Jurik on 27.6.2015.
 */
public enum  Utils {
    INST;

    private LocalDate startDate;
    private LocalDateTime startDateTime;

    Utils() {
        startDate = LocalDate.now();
        startDateTime = LocalDateTime.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
}

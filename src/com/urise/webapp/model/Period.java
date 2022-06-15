package com.urise.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Period extends Organization {
    private final LocalDate start;
    private final LocalDate end;
    private final String title;
    private final String description;

    public Period(LocalDate start, LocalDate end, String title, String description) {
        this.start = Objects.requireNonNull(start, "Start date must not be null");
        this.end = Objects.requireNonNull(end, "End date must not be null");
        this.title = Objects.requireNonNull(title, "Title must not be null");
        this.description = description;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return this.toString();
    }
}

package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization extends OrganizationSection {
    private final String title;
    private String url;
    private final List<Period> periods;

    public Organization() {
        title = "";
        periods = new ArrayList<>();
    }

    public Organization(String title, List<Period> periods) {
        this.title = Objects.requireNonNull(title, "Title must not be null");
        this.periods = Objects.requireNonNull(periods, "Periods must not be null");
    }

    public Organization(String title, String url, List<Period> periods) {
        this(title, periods);
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getContent() {
        return title + " : " + url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Organization that = (Organization) o;
        return title.equals(that.title) && url.equals(that.url) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, url, periods);
    }
}

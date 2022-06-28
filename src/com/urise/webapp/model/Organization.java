package com.urise.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Organization extends OrganizationSection implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private String url;
    private final List<Period> periods;

    public Organization() {
        name = "";
        periods = new ArrayList<>();
    }

    public Organization(String name, List<Period> periods) {
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.periods = Objects.requireNonNull(periods, "Periods must not be null");
    }

    public Organization(String name, String url, List<Period> periods) {
        this(name, periods);
        this.url = url == null ? "" : url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getContent() {
        return name + " : " + url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, url, periods);
    }
}

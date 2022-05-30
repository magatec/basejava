package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organisation extends OrganisationSection {
    private String title;

    private String url;

    private List<Period> periods;

    public Organisation(String title) {
        this.title = title;
    }

    public Organisation(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public Organisation(String title, List<Period> periods) {
        this.title = title;
        this.periods = periods;
    }

    public Organisation(String title, String url, List<Period> periods) {
        this.title = title;
        this.url = url;
        this.periods = periods;
    }

    public Organisation() {
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
        Organisation that = (Organisation) o;
        return title.equals(that.title) && url.equals(that.url) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, url, periods);
    }
}

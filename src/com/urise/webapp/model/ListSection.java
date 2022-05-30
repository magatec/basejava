package com.urise.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection {

    private final List<String> list;

    public ListSection(List<String> list) {
        this.list = list;
    }

    public String getContent() {
        return list.toString();
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return list.equals(that.list);
    }
}

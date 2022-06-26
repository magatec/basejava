package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private List<String> items;

    public List<String> getItems() {
        return items;
    }

    public ListSection() {
    }

    public ListSection(List<String> items) {
        this.items = Objects.requireNonNull(items, "Items must not be null");
    }

    public String getContent() {
        return items.toString();
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return items.equals(that.items);
    }
}

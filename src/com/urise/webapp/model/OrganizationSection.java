package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    private List<Organization> organizations;

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = Objects.requireNonNull(organizations, "Organizations must not be null");
    }

    public OrganizationSection() {
    }

    public List<Organization> getList() {
        return organizations;
    }

    public String getContent() {
        return organizations.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}

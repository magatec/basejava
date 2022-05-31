package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganisationSection extends AbstractSection {

    private List<Organisation> organisationList;

    public OrganisationSection(List<Organisation> organisationList) {
        this.organisationList = organisationList;
    }

    public OrganisationSection() {
    }

    public List<Organisation> getList() {
        return organisationList;
    }

    public String getContent() {
        return organisationList.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganisationSection that = (OrganisationSection) o;
        return organisationList.equals(that.organisationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisationList);
    }
}

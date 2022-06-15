package com.urise.webapp.model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String uuid;
    private final String fullName;

    private String contacts;

    protected EnumMap<ContactType, String> contactsMap = new EnumMap<>(ContactType.class);

    protected EnumMap<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);


    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName, EnumMap<ContactType, String> contactsMap) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.contactsMap = contactsMap;
    }

    public Resume(String uuid, String fullName, EnumMap<ContactType, String> contactsMap, EnumMap<SectionType, AbstractSection> sections) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.contactsMap = contactsMap;
        this.sections = sections;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contactsMap;
    }

    public void setContacts(ContactType type, String contact) {
        contactsMap.put(type, contact);
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void setSections(SectionType type, AbstractSection section) {
        sections.put(type, section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

}

package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {
    private static final long serialVersionUID = 1L;
    private String uuid;
    private String fullName;

    private String contacts;

    protected Map<ContactType, String> contactsMap = new EnumMap<>(ContactType.class);

    protected Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume() {
    }

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
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return uuid;
    }

}

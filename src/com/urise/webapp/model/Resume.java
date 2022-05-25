package com.urise.webapp.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private final String fullName;

    public Map<String, String> contacts;

    public String personal;

    public String objective;

    public List<String> achievement;

    public List<String> qualifications;

    public Map<String, Map<String, String>> experience;

    public Map<String, Map<String, String>> education;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

//    public Map<String, String> getContacts() {return contacts;};

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

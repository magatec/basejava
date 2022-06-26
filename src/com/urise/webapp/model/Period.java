package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period extends Organization {
//    private static final long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate start;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate end;
    private String title;
    private String description;

    public Period() {
    }

    public Period(LocalDate start, LocalDate end, String title, String description) {
        this.start = Objects.requireNonNull(start, "Start date must not be null");
        this.end = Objects.requireNonNull(end, "End date must not be null");
        this.title = Objects.requireNonNull(title, "Title must not be null");
        this.description = description;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return this.toString();
    }
}

package com.urise.webapp.model;

public enum ContactSection {
    TELEPHONE("Тел.:"),
    SKYPE("Skype:"),
    MAIL("Почта:"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

    ContactSection(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

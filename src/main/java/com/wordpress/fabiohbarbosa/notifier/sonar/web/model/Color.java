package com.wordpress.fabiohbarbosa.notifier.sonar.web.model;

public enum Color {
    ISSUES_COLOR("#4d94ff"),
    PROJECT_COLOR("#e6b800"),
    SCM_COLOR("#e6b800"),
    RATING_A("#0A0"), RATING_B("#80CC00"), RATING_C("#FE0"), RATING_D("#F77700"), E("#E00");

    public String VALUE;

    Color(final String VALUE){
        this.VALUE = VALUE;
    }
}

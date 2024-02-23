package ru.ok.spring.models;

public enum Level {
    TOP("TOP")
    ,HIGH("HIGH")
    ,MIDDLE("MIDDLE")
    ,LOW("LOW")
    ;
    private final String value;

    public String getValue() {
        return value;
    }

    Level(String value) {
        this.value = value;
    }
}

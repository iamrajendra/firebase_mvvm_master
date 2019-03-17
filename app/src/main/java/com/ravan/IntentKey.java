package com.ravan;

public enum IntentKey {
    MOBILE_NO("MOBILE_NO");

    private final String text;

    IntentKey(String text) {
    this.text = text;
    }

    public String getKey() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}

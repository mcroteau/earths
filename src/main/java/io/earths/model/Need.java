package io.earths.model;

public class Need {
    public Need(String value, String description) {
        this.value = value;
        this.description = description;
    }

    String value;
    String description;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

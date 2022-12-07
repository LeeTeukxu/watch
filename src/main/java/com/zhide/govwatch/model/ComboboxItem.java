package com.zhide.govwatch.model;

import java.io.Serializable;

public class ComboboxItem implements Serializable {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String id;
    private String text;
}

package com.example;

public class GptResponse {
    public String id;
    public String object;
    public long created;
    public String model;
    public Choice[] choices;
    
    public String getId() {
        return this.id;
    }

    public String getObject() {
        return this.object;
    }

    public long getCreated() {
        return this.created;
    }

    public String getModel() {
        return this.model;
    }

    public Choice[] getChoices() {
        return this.choices;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }
}
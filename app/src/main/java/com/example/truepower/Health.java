package com.example.truepower;

public class Health {

    private String id;
    private String name;
    private String description;
    private String nextDate;
    private String lastDate;

    public Health() {
    }

    public Health(String id, String name, String description, String nextDate, String lastDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nextDate = nextDate;
        this.lastDate = lastDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }
}
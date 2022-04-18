package com.example.truepower;
import java.io.Serializable;


public class Routine implements Serializable {
    String id,name,description,status,date,obId;

    public Routine(){

    }

    public String getObId() {
        return obId;
    }

    public void setObId(String obId) {
        this.obId = obId;
    }

    public Routine(String id, String name, String description, String status, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.date = date;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

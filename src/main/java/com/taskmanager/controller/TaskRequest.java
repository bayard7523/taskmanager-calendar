package com.taskmanager.controller;

import java.time.LocalTime;
import java.util.Date;

public class TaskRequest {
    private String title;
    private String description;
    private Date date;
    private LocalTime time;

    public TaskRequest() {
    }

    public TaskRequest(String title, String description, Date date, LocalTime time) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}

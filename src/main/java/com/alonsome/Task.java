package com.alonsome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task {
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private String createdAt;
    private String dueDate;

    TaskService taskService = new TaskService();

    public Task(){

    }

    public Task(String title, String description) {
        this.id = IDGenerator.getNextID(taskService.getTasks());
        this.title = title;
        this.description = description;
        this.completed = false;
        this.createdAt = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        this.dueDate = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", createdAt='" + createdAt + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setUuid(int id) {
        this.id = id;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}

class IDGenerator {
    public static synchronized int getNextID(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return 1;
        }
        int maxId = 0;
        for (Task task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        return maxId + 1;
    }
}

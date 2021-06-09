package com.taskmanager.service;

import com.taskmanager.model.Task;

import java.util.List;

public interface ITaskService {
    void createTask(Task task);

    List<Task> getTasksByDate(String date);

    void deleteTask(Long id);
}

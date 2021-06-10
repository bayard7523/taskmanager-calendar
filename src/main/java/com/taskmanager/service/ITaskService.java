package com.taskmanager.service;

import com.taskmanager.model.Task;
import javassist.tools.reflect.CannotCreateException;

import java.util.List;

public interface ITaskService {
    void createTask(Task task) throws CannotCreateException;

    List<Task> getTasksByDate(String date);

    void deleteTask(Long id);
}

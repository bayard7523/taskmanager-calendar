package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.repository.ITaskRepository;
import javassist.tools.reflect.CannotCreateException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements ITaskService {
    private final ITaskRepository iTaskRepository;

    public TaskService(ITaskRepository iTaskRepository) {
        this.iTaskRepository = iTaskRepository;
    }

    @Override
    public void createTask(Task task) throws CannotCreateException {
        if (task.getTitle().isEmpty()) {
            throw new CannotCreateException("Cannot create task with: Title = " + task.getTitle());
        }
        task.setId(null == iTaskRepository.findMaxId() ? 1 : iTaskRepository.findMaxId() + 1);
        iTaskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByDate(String date) {
        return new ArrayList<>(iTaskRepository.getTasksByDate(date));
    }

    @Override
    public void deleteTask(Long id) {
        if (iTaskRepository.findById(id).isPresent()) {
            iTaskRepository.deleteById(id);
        }
    }
}

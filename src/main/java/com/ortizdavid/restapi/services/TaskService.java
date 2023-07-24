package com.ortizdavid.restapi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ortizdavid.restapi.entities.Task;
import com.ortizdavid.restapi.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(Long id) {
        return taskRepository.findById(id)
                            .orElseThrow();
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Task taskDetails, Long id) {
        Task task = this.getById(id);
        task.setUserId(taskDetails.getUserId());
        task.setTaskName(taskDetails.getTaskName());
        task.setStartDate(taskDetails.getStartDate());
        task.setEndDate(taskDetails.getEndDate());
        task.setDescription(taskDetails.getDescription());
        task.setComplexity(taskDetails.getComplexity());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        Task task = this.getById(id);
        taskRepository.delete(task);
    }
    
}

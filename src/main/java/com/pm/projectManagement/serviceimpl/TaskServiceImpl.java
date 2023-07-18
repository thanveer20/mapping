package com.pm.projectManagement.serviceimpl;

import com.pm.projectManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.projectManagement.entity.Task;
import com.pm.projectManagement.repo.TaskRepo;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepository) {
        this.taskRepo = taskRepository;
    }
    
    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepo.findById(id).orElse(null);

        if (task != null) {
            task.setId(taskDetails.getId());
            task.setName(taskDetails.getName());
            task.setDescription(taskDetails.getDescription());
            task.setAssignedTo(taskDetails.getAssignedTo());
            task.setPriority(taskDetails.getPriority());
            task.setDeadline(taskDetails.getDeadline());
            task.setStatus(taskDetails.getStatus());
            return taskRepo.save(task);
        }

        return null;
    }

    @Override
    public void deleteTask(Long id) {
    	taskRepo.deleteById(id);
    }

}
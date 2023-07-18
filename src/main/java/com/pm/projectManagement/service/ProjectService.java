package com.pm.projectManagement.service;

import com.pm.projectManagement.entity.Project;
import com.pm.projectManagement.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ProjectService {
    List<Project> getAllProjects();
    Project createProject(Project project);
    Project getProjectById(Long id);
    Project updateProject(Long id, Project ProjectDetails);
    Project deleteProject(Long id);
    List<Task> getTaskByProjectId(Long id);
    Task createTaskByProjectId(Long id, Task task);
}


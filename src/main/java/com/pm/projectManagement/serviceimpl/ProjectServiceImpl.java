package com.pm.projectManagement.serviceimpl;

import com.pm.projectManagement.entity.Project;
import com.pm.projectManagement.entity.Task;
import com.pm.projectManagement.repo.ProjectRepo;
import com.pm.projectManagement.repo.TaskRepo;
import com.pm.projectManagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private TaskRepo taskrepo;
    @Autowired
    private ProjectRepo projectRepo;
    public ProjectServiceImpl(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public Project createProject(Project project) {
        System.out.println(project);
        return projectRepo.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepo.findById(id).orElse(null);
    }

    @Override
    public Project updateProject(Long id, Project ProjectDetails) {
        Project project = projectRepo.findById(id).orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        if (project != null) {
            project.setProjectName(ProjectDetails.getProjectName());
            project.setProjectDescription(ProjectDetails.getProjectDescription());
            project.setProjectStartDate(ProjectDetails.getProjectStartDate());
            project.setProjectEndDate(ProjectDetails.getProjectEndDate());
            return projectRepo.save(project);
        }
        return null;

    }

    @Override
    public Project deleteProject(Long id) {
        projectRepo.deleteById(id);
        return null;
    }

    @Override
    public List<Task> getTaskByProjectId(Long id) {
        return taskrepo.getTaskByProjectId(id);
    }

    @Override
    public Task createTaskByProjectId(Long id, Task task) {
        return null;
    }

//    @Override
//    public Task createTaskByProjectId(Long id, Task task) {
//        Project project = projectRepo.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));
//        project.setTaskList(task);
//        return taskrepo.save(project.setTaskList(List<task> taskList));
//    }
}
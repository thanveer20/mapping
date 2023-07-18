package com.pm.projectManagement.controller;

import com.pm.projectManagement.entity.Project;
import com.pm.projectManagement.entity.Task;
import com.pm.projectManagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000" ,maxAge =3600)
@RestController
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping("/api/projects")
	public ResponseEntity<List<Project>> getAllProjects() {
		return new ResponseEntity<List<Project>>(projectService.getAllProjects(), HttpStatus.OK);
	}

	@PostMapping("/api/projects")
	public ResponseEntity<Project> createProject(@RequestBody Project project) {
		if (project != null) {

			return new ResponseEntity<>(projectService.createProject(project), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/api/projects/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
		if (id != null) {
			return new ResponseEntity<Project>(projectService.getProjectById(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/api/projects/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable Long id,@RequestBody Project ProjectDetails) {
		if (id != null) {
			return new ResponseEntity<Project>(projectService.updateProject(id, ProjectDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@DeleteMapping("/api/projects/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
		if(id != null) {
			projectService.deleteProject(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/api/projects/{id}/tasks")
	public ResponseEntity<List<Task>> updateTask(@PathVariable long id) {
		return new ResponseEntity<List<Task>>(projectService.getTaskByProjectId(id),HttpStatus.OK);
	}

	@PostMapping("/api/projects/{id}/tasks")
	public ResponseEntity<Task> createTask(@PathVariable long id, @RequestBody Task task) {
		return new ResponseEntity<>(projectService.createTaskByProjectId(id, task),HttpStatus.CREATED);

	}

//	@GetMapping("/api/projects/{projectId}/tasks")
//	public ResponseEntity<List<Task>> updateTask(@PathVariable long projectId) {
//			return new ResponseEntity<List<Task>>(projectService.getTaskByProjectId(projectId),HttpStatus.OK);
//	}

//	@PostMapping("/api/projects/{id}/tasks")
//	public ResponseEntity<Project> createTask(@PathVariable long id, @RequestBody Task task) {
//		return new ResponseEntity<>(projectService.createTaskByProjectId(id, task),HttpStatus.CREATED);
//	}
}

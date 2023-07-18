package com.pm.projectManagement.controller;

import com.pm.projectManagement.entity.Task;
import com.pm.projectManagement.repo.ProjectRepo;
import com.pm.projectManagement.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaskController {
	
	@Autowired
	TaskRepo taskRepo;
	
	@Autowired
	ProjectRepo projectRepo;
	
	@PostMapping("/api/projects/tasks")
	public ResponseEntity<Task> saveTask(@RequestBody Task task) {
		return new ResponseEntity<> (taskRepo.save(task),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/api/tasks/{id}")
	public ResponseEntity<Task> getTask(@PathVariable long id) {
		Optional<Task> task = taskRepo.findById(id);
		if(task.isPresent()) {
			return new ResponseEntity<>(task.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/api/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable long id,@RequestBody Task tas) {
		Optional<Task> task = taskRepo.findById(id);
		if(task.isPresent()) {
			task.get().setName(tas.getName());
			task.get().setDescription(tas.getDescription());
			task.get().setAssignedTo(tas.getAssignedTo());
			task.get().setPriority(tas.getPriority());
			task.get().setDeadline(tas.getDeadline());
			task.get().setStatus(tas.getStatus());
			return new ResponseEntity<>(taskRepo.save(task.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/tasks/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable long id) {
		Optional<Task> task = taskRepo.findById(id);
		if(task.isPresent()) {
			taskRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}

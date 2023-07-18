package com.pm.projectManagement.repo;

import com.pm.projectManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query(value = "select *from task where project_project_id =:id", nativeQuery = true)
    List<Task> getTaskByProjectId(Long id);
}
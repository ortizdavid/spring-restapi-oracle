package com.ortizdavid.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ortizdavid.restapi.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}

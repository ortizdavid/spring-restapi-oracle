package com.ortizdavid.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ortizdavid.restapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}

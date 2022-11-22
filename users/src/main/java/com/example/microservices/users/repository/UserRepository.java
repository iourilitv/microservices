package com.example.microservices.users.repository;

import com.example.microservices.users.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}

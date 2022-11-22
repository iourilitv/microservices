package com.example.microservices.users.service;

import com.example.microservices.users.entity.City;
import com.example.microservices.users.entity.User;
import com.example.microservices.users.enums.Gender;
import com.example.microservices.users.repository.CityRepository;
import com.example.microservices.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public List<User> getAll() {
        User user = new User("Yury", "Petrov", Gender.MALE, new Date(), new City("Moscow"), "iuric");
        return List.of(user);
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String createUser(User user) {
        User savedUser = userRepository.save(user);
        return String.format("User %s has been saved with id=%s", savedUser.getLastName(), savedUser.getId());
    }
}

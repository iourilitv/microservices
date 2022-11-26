package com.example.microservices.users.controller;

import com.example.microservices.users.dto.UserDTO;
import com.example.microservices.users.entity.User;
import com.example.microservices.users.mapper.UserMapper;
import com.example.microservices.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDTO> getAll() {
        return userMapper.toDTOList(userService.getAll());
    }

    @GetMapping(value = "/{id}")
    public UserDTO getUser(@PathVariable long id) {
        return userMapper.toDTO(userService.getUser(id));
    }

    @PutMapping(value = "/{id}")
    public String updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        User user = userMapper.toEntity(userDTO);
        validateUserWithId(user, id);
        return userService.updateUser(user);
    }

    @PostMapping
    public String createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userService.createUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    private void validateUserWithId(User user, Long id) {
        if (!Objects.equals(user.getId(), id)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}

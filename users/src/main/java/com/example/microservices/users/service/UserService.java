package com.example.microservices.users.service;

import com.example.microservices.users.entity.User;
import com.example.microservices.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        //TODO It's probably better to do this with a sql query on the repository
        List<User> users = (List<User>) userRepository.findAll();
        users.forEach(user -> {
            reduceFollowingsExcludeFollowWithDeletedUsers(user);
            reduceFollowersExcludeFollowWithDeletedUsers(user);
        });
        return users;
    }

    public User getUser(long id) {
        //TODO It's probably better to do this with a sql query on the repository
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        reduceFollowingsExcludeFollowWithDeletedUsers(user);
        reduceFollowersExcludeFollowWithDeletedUsers(user);
        return user;
    }

    public String updateUser(User user) {
        User savedUser = userRepository.save(user);
        return String.format("User(id: %s, nickname: %s) has been updated successfully", savedUser.getId(), savedUser.getNickname());
    }

    public String createUser(User user) {
        if (userRepository.findByNickname(user.getNickname()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
        User savedUser = userRepository.save(user);
        return String.format("New user(nickname: %s) has been saved with id: %s", savedUser.getNickname(), savedUser.getId());
    }

    @Transactional
    public String deleteUser(Long id) {
        Optional<User> userInDbOptional = userRepository.findById(id);
        if (userInDbOptional.isEmpty() || userInDbOptional.get().getDeletedAt() != null) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
        User user = userInDbOptional.get();
        userRepository.delete(user);
        return String.format("New user(id: %s, nickname: %s) has been deleted", user.getId(), user.getNickname());
    }

    private void reduceFollowingsExcludeFollowWithDeletedUsers(User user) {
        user.setFollowings(user.getFollowings().stream().filter(f -> {
            Optional<User> followingOptional = userRepository.findById(f.getFollowingId());
            return followingOptional.isPresent() && followingOptional.get().getDeletedAt() == null;
        }).collect(Collectors.toSet()));
    }

    private void reduceFollowersExcludeFollowWithDeletedUsers(User user) {
        user.setFollowers(user.getFollowers().stream().filter(f -> {
            Optional<User> followerOptional = userRepository.findById(f.getFollowerId());
            return followerOptional.isPresent() && followerOptional.get().getDeletedAt() == null;
        }).collect(Collectors.toSet()));
    }
}

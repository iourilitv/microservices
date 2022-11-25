package com.example.microservices.users.service;

import com.example.microservices.users.entity.Follow;
import com.example.microservices.users.entity.User;
import com.example.microservices.users.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;

    //TODO It's required to handle Soft Deleted User case for all get methods

    public List<Follow> getAll() {
        return (List<Follow>) followRepository.findAll();
    }

    public List<Follow> getAllFollowings(Long followerId) {
        return followRepository.findAllByFollowerId(followerId);
    }

    public List<Follow> getAllFollowers(Long followingId) {
        return followRepository.findAllByFollowingId(followingId);
    }

    public Follow getFollow(long id) {
        return followRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public String createFollow(Follow follow) {
        if (follow.getId() != null
                || Objects.equals(follow.getFollowingId(), follow.getFollowerId())
                || followRepository.findByFollowingIdAndFollowerId(follow.getFollowingId(), follow.getFollowerId()).isPresent()
                    ) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
        follow.setFollowedAt(follow.getFollowedAt());
        Follow savedFollow = followRepository.save(follow);
        User following = userService.getUser(follow.getFollowingId());
        User follower = userService.getUser(follow.getFollowerId());
        return String.format("User(id: %s, nickname: %s) has been followed to User(id: %s, nickname: %s) with Follow(id: %s)",
                following.getId(), following.getNickname(), follower.getId(), follower.getNickname(), savedFollow.getId());
    }

    @Transactional
    public String deleteFollow(Long id) {
        Optional<Follow> existFollowOptional = followRepository.findById(id);
        if (existFollowOptional.isPresent()) {
            User following = userService.getUser(existFollowOptional.get().getFollowingId());
            User follower = userService.getUser(existFollowOptional.get().getFollowerId());
            followRepository.deleteById(id);
            return String.format("User(id: %s, nickname: %s) has been followed to User(id: %s, nickname: %s) with Follow(id: %s)",
                    following.getId(), following.getNickname(), follower.getId(), follower.getNickname(), existFollowOptional.get().getId());
        }
        return String.format("There is no Follow to delete with id: %s", id);
    }
}

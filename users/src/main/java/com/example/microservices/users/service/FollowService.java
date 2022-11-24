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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;

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
    public String createFollow(Long followingId, Long followerId) {
        if (followRepository.findByFollowingIdAndFollowerId(followingId, followerId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
        User following = userService.getUser(followingId);
        User follower = userService.getUser(followerId);
        Follow follow = new Follow(following, follower);
        Follow savedFollow = followRepository.save(follow);
        return String.format("User(id: %s, nickname: %s) has been followed to User(id: %s, nickname: %s) with Follow(id: %s)",
                following.getId(), following.getNickname(), follower.getId(), follower.getNickname(), savedFollow.getId());
    }

    @Transactional
    public String deleteFollow(Long id) {
        Optional<Follow> existFollowOptional = followRepository.findById(id);
        if (existFollowOptional.isPresent()) {
            User following = existFollowOptional.get().getFollowing();
            User follower = existFollowOptional.get().getFollower();
            followRepository.deleteById(id);
            return String.format("User(id: %s, nickname: %s) has been followed to User(id: %s, nickname: %s) with Follow(id: %s)",
                    following.getId(), following.getNickname(), follower.getId(), follower.getNickname(), existFollowOptional.get().getId());
        }
        return String.format("There is no Follow to delete with id: %s", id);
    }
//    public String createFollow(Follow follow) {
//        if (followRepository.findByFollowingIdAndFollowerId(follow.getFollowingId(), follow.getFollowerId()).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
//        }
//        follow.setFollowedAt(follow.getFollowedAt());
//        Follow savedFollow = followRepository.save(follow);
//        User following = userService.getUser(follow.getFollowingId());
//        following.getFollowers().add(savedFollow);
//        User follower = userService.getUser(follow.getFollowerId());
//        follower.getFollowings().add(savedFollow);
//        return String.format("User(id: %s, nickname: %s) has been followed to User(id: %s, nickname: %s) with Follow(id: %s)",
//                following.getId(), following.getNickname(), follower.getId(), follower.getNickname(), savedFollow.getId());
//    }
}

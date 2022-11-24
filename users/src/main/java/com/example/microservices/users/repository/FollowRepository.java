package com.example.microservices.users.repository;

import com.example.microservices.users.entity.Follow;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends CrudRepository<Follow, Long> {
    Optional<Follow> findByFollowingIdAndFollowerId(Long followingId, Long followerId);
    List<Follow> findAllByFollowingId(Long followingId);
    List<Follow> findAllByFollowerId(Long followerId);
}

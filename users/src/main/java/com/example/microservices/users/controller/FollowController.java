package com.example.microservices.users.controller;

import com.example.microservices.users.entity.Follow;
import com.example.microservices.users.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/follows")
public class FollowController {

    private final FollowService followService;

    @GetMapping
    public List<Follow> getAll() {
        return followService.getAll();
    }

    @GetMapping("/followings/{followerId}")
    public List<Follow> getAllFollowings(@PathVariable Long followerId) {
        return followService.getAllFollowings(followerId);
    }

    @GetMapping("/followers/{followingId}")
    public List<Follow> getAllFollowers(@PathVariable Long followingId) {
        return followService.getAllFollowers(followingId);
    }

    @GetMapping(value = "/{id}")
    public Follow getFollow(@PathVariable long id) {
        return followService.getFollow(id);
    }

    @PostMapping(value = "/following/{followingId}/follower/{followerId}")
    public String createFollow(@PathVariable Long followingId, @PathVariable Long followerId) {
        return followService.createFollow(followingId, followerId);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteFollow(@PathVariable Long id) {
        return followService.deleteFollow(id);
    }
}

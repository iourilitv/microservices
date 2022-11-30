package com.example.microservices.users.util;

import com.example.microservices.users.entity.Follow;
import com.example.microservices.users.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FollowTestUtils {

    public static Set<Follow> createFollowSetForUsers(List<User> users) {
        Random random = new Random();
        final Set<Follow> set = new HashSet<>();
        for (int i = 0; i < users.size(); i++) {
            long followingId = random.nextInt(users.size());
            long followerId = random.nextInt(users.size());
            if (followingId != followerId) {
                set.add(new TestFollow(100L + i, followingId, followerId));
            }
        }
        return set;
    }

    public static void fillUpTestFollows(int size, List<Follow> testFollows) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            testFollows.add(createTestFollow(random.nextInt(size)));
        }
    }

    private static Follow createTestFollow(int i) {
        long followingId, followerId;
        if (i % 2 == 0) {
            followingId = 1L + i;
            followerId = 2L + i;
        } else {
            followingId = 2L + i;
            followerId = 3L + i;
        }
        return new TestFollow(10L + i, followingId, followerId);
    }

    public static class TestFollow extends Follow {
        private final Long id;

        public TestFollow(Long id, Long followingId, Long followerId) {
            super(followingId, followerId);
            this.id = id;
        }

        @Override
        public Long getId() {
            return this.id;
        }
    }
}

package com.example.microservices.users.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "following_id", nullable = false, updatable = false)
    private Long followingId;

    @Column(name = "follower_id", nullable = false, updatable = false)
    private Long followerId;

    @Column(name = "followed_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date followedAt;

    public Follow(Long followingId, Long followerId) {
        this.followingId = followingId;
        this.followerId = followerId;
        this.followedAt = new Date();
    }

    public void setFollowedAt(Date followedAt) {
        this.followedAt = followedAt == null ? new Date() : followedAt;
    }
}

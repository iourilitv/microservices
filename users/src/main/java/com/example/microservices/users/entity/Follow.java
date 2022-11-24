package com.example.microservices.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

//    @Column(name = "following_id", nullable = false, updatable = false)
//    private Long followingId;
    // To solve the problem: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: java.util.ArrayList[0]->com.example.microservices.users.entity.Follow["following"]->com.example.microservices.users.entity.User$HibernateProxy$32kmKfuG["hibernateLazyInitializer"])
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "following_id", nullable = false, updatable = false)
    private @Setter User following;

//    @Column(name = "follower_id", nullable = false, updatable = false)
//    private Long followerId;
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "follower_id", nullable = false, updatable = false)
    private @Setter User follower;

    @Column(name = "followed_at", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date followedAt;

    @Column(name = "unfollowed_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date unfollowedAt;

    public Follow(User following, User follower) {
        this.following = following;
        this.follower = follower;
        this.followedAt = new Date();
    }
    //    public Follow(Long followingId, Long followerId) {
//        this.followingId = followingId;
//        this.followerId = followerId;
//        this.followedAt = new Date();
//    }

    public void setFollowedAt(Date followedAt) {
        this.followedAt = followedAt == null ? new Date() : followedAt;
    }

    public void setUnfollowedAt(Date unfollowedAt) {
        this.unfollowedAt = unfollowedAt == null ? new Date() : unfollowedAt;
    }
}

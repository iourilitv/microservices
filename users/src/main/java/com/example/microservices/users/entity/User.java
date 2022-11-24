package com.example.microservices.users.entity;

import com.example.microservices.users.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(exclude = {"followings", "followers"})
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "gender_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "current_city_id", nullable = false)
    private City currentCity;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "about")
    private String about;

    @Column(name = "hard_skills")
    private String hardSkills;

    @OneToMany
    @JoinColumn(name = "follower_id", updatable = false)
    @OrderBy("id")
    private Set<Follow> followings;

    @OneToMany
    @JoinColumn(name = "following_id", updatable = false)
    @OrderBy("id")
    private Set<Follow> followers;

    public User(String firstName, String lastName, Gender gender, Date birthday, City currentCity, String nickname) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.currentCity = currentCity;
        this.nickname = nickname;
    }
}

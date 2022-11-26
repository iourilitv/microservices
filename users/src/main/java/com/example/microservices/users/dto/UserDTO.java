package com.example.microservices.users.dto;

import com.example.microservices.users.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
@EqualsAndHashCode
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;

    @JsonProperty(required = true)
    private String firstName;

    @JsonProperty(required = true)
    private String lastName;

    private String secondName;

    @JsonProperty(required = true)
    private Gender gender;

    @JsonProperty(required = true)
    private Date birthday;

    @JsonProperty(required = true)
    private CityDTO currentCity;

    @JsonProperty(required = true)
    private String nickname;

    private String email;

    private String phone;

    private String about;

    private String hardSkills;

    private Integer followingsNumber;

    private Integer followersNumber;

}

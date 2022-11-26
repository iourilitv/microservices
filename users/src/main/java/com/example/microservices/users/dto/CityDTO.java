package com.example.microservices.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CityDTO {

    @JsonProperty(required = true)
    private Integer id;

    private String name;
}

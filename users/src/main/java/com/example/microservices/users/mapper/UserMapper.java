package com.example.microservices.users.mapper;

import com.example.microservices.users.dto.UserDTO;
import com.example.microservices.users.entity.User;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Sources:
 * Used here - Quick Guide to MapStruct: https://www.baeldung.com/mapstruct
 * Custom Mapper with MapStruct: https://www.baeldung.com/mapstruct-custom-mapper
 */

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User entity);

    User toEntity(UserDTO dto);

    List<UserDTO> toDTOList(List<User> entityList);

    List<User> toEntityList(List<UserDTO> dtoList);

    @BeforeMapping
    default void getNumbers(User user, @MappingTarget UserDTO userDTO) {
        userDTO.setFollowingsNumber(user.getFollowings().size());
        userDTO.setFollowersNumber(user.getFollowers().size());
    }
}

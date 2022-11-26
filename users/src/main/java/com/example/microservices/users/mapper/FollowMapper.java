package com.example.microservices.users.mapper;

import com.example.microservices.users.dto.FollowDTO;
import com.example.microservices.users.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);

    FollowDTO toDTO(Follow entity);

    Follow toEntity(FollowDTO dto);

    List<FollowDTO> toDTOList(List<Follow> entityList);

    List<Follow> toEntityList(List<FollowDTO> dtoList);
}

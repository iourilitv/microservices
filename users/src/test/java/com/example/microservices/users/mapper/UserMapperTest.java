package com.example.microservices.users.mapper;

import com.example.microservices.users.dto.CityDTO;
import com.example.microservices.users.dto.UserDTO;
import com.example.microservices.users.entity.City;
import com.example.microservices.users.entity.Follow;
import com.example.microservices.users.entity.User;
import com.example.microservices.users.enums.Gender;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    CityMapper cityMapper = Mappers.getMapper(CityMapper.class);
    UserMapper userMapper = new UserMapperImpl(cityMapper);

    @Test
    void test1_givenEntity_thenCorrect_toDTO() {
        User user = createTestUser(1);
        UserDTO userDTO = userMapper.toDTO(user);
        assertEntityToDTO(user, userDTO);
    }

    @Test
    void test2_givenDTO_thenCorrect_toEntity() {
        UserDTO userDTO = createTestUserDTO(1);
        User user = userMapper.toEntity(userDTO);
        assertDTOtoEntity(userDTO, user);
    }

    @Test
    void test3_givenEntityList_thenCorrect_toDTOList() {
        for (int i = 0; i < 5; i++) {
            User user = createTestUser(i);
            UserDTO userDTO = userMapper.toDTO(user);
            assertEntityToDTO(user, userDTO);
        }
    }

    @Test
    void test4_givenEntityList_thenCorrect_toEntityList() {
        for (int i = 0; i < 5; i++) {
            UserDTO userDTO = createTestUserDTO(i);
            User user = userMapper.toEntity(userDTO);
            assertDTOtoEntity(userDTO, user);
        }
    }

    private User createTestUser(int index) {
        Long userId = 10L + index;
        User user = new TestUser(
                userId,
                "test_firstName" + index,
                "test_lastName" + index,
                Gender.values()[index % Gender.values().length],
                new Date(),
                new City(100 + index, "test_cityName" + 100 + index),
                "my_nick_name" + index);
        user.setEmail("testEmail@mail.com" + index);
        user.setPhone("+7(999) 999-9999" + index);
        user.setAbout("Test About" + index);
        user.setHardSkills("Test Hard Skills" + index);
        user.setFollowings(Set.of(new Follow(1L, userId)));
        user.setFollowers(Set.of(new Follow(userId, 4L), new Follow(userId, 2L)));
        return user;
    }

    private UserDTO createTestUserDTO(int index) {
        UserDTO userDTO = new UserDTO(
                "test_firstName" + index,
                "test_lastName" + index,
                Gender.values()[index % Gender.values().length],
                new Date(),
                new CityDTO(100 + index),
                "my_nick_name" + index);
        userDTO.setEmail("testEmail@mail.com" + index);
        userDTO.setPhone("+7(999) 999-9999" + index);
        userDTO.setAbout("Test About" + index);
        userDTO.setHardSkills("Test Hard Skills" + index);
        return userDTO;
    }

    private void assertEntityToDTO(User user, UserDTO userDTO) {
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getGender(), userDTO.getGender());
        assertEquals(user.getBirthday(), userDTO.getBirthday());
        assertEquals(user.getCurrentCity().getId(), userDTO.getCurrentCity().getId());
        assertEquals(user.getNickname(), userDTO.getNickname());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getPhone(), userDTO.getPhone());
        assertEquals(user.getAbout(), user.getAbout());
        assertEquals(user.getHardSkills(), userDTO.getHardSkills());
        assertEquals(user.getFollowings().size(), userDTO.getFollowingsNumber());
        assertEquals(user.getFollowers().size(), userDTO.getFollowersNumber());
    }

    private void assertDTOtoEntity(UserDTO userDTO, User user) {
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getFirstName(), user.getFirstName());
        assertEquals(userDTO.getLastName(), user.getLastName());
        assertEquals(userDTO.getGender(), user.getGender());
        assertEquals(userDTO.getBirthday(), user.getBirthday());
        assertEquals(userDTO.getCurrentCity().getId(), user.getCurrentCity().getId());
        assertEquals(userDTO.getNickname(), user.getNickname());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getPhone(), user.getPhone());
        assertEquals(userDTO.getAbout(), user.getAbout());
        assertEquals(userDTO.getHardSkills(), user.getHardSkills());
    }

    private static class TestUser extends User {
        private final Long id;

        public TestUser(Long id, String firstName, String lastName, Gender gender, Date birthday, City currentCity, String nickname) {
            super(firstName, lastName, gender, birthday, currentCity, nickname);
            this.id = id;
        }

        @Override
        public Long getId() {
            return this.id;
        }
    }
}
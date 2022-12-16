package com.example.microservices.users.controller;

import com.example.microservices.users.UsersApplication;
import com.example.microservices.users.dto.FollowDTO;
import com.example.microservices.users.entity.City;
import com.example.microservices.users.entity.Follow;
import com.example.microservices.users.entity.User;
import com.example.microservices.users.repository.CityRepository;
import com.example.microservices.users.util.FollowTestUtils;
import com.example.microservices.users.util.ITestUtilPostgreSQLContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.microservices.users.util.FollowTestUtils.fillUpFollowsForUsers;
import static com.example.microservices.users.util.MapperTestUtils.initMapper;
import static com.example.microservices.users.util.UserTestUtils.fillUpUsers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Module tests
 */

@Transactional
@ActiveProfiles(profiles = "integration-test")
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@TestMethodOrder(value = MethodOrderer.MethodName.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = UsersApplication.class)
@Testcontainers(disabledWithoutDocker = true)
class ITestFollowController {
    private static final int TEST_USERS_SIZE = 3;
    private static final int TEST_FOLLOWS_SIZE = TEST_USERS_SIZE * TEST_USERS_SIZE;

    @Container
    public static PostgreSQLContainer<?> sqlContainer = ITestUtilPostgreSQLContainer.getInstance();
    private @Autowired MockMvc mockMvc;
    private @Autowired EntityManager entityManager;
    private @Autowired CityRepository cityRepository;
    private static final ObjectMapper mapper = initMapper();

    private List<City> cities;
    private final List<User> users = new ArrayList<>(TEST_USERS_SIZE);
    private final List<Follow> follows = new ArrayList<>(TEST_FOLLOWS_SIZE);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cities = (List<City>) cityRepository.findAll();
        fillUpUsers(TEST_USERS_SIZE, users, cities);
        users.forEach(this::storeUser);
        fillUpFollowsForUsers(TEST_FOLLOWS_SIZE, follows, users);
        follows.forEach(this::storeFollow);
    }

    @AfterEach
    void tearDown() {
        users.clear();
        follows.clear();
    }

    @Test
    public void test0_givenRequestForHealth_thenCorrect_health() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.equalTo("UP")));
    }

    @Test
    public void test1_thenCorrect_getAll() throws Exception {
        List<FollowDTO> dtoList = follows.stream().map(FollowTestUtils::toFollowDTO).collect(Collectors.toList());
        String expectedJson = mapper.writeValueAsString(dtoList);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/follows")
                        .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        String actualJson = response.getContentAsString();
        assertEquals(expectedJson, actualJson);
    }

//    @GetMapping("/followings/{followerId}")
//    public List<FollowDTO> getAllFollowings(@PathVariable Long followerId) {
//        return followMapper.toDTOList(followService.getAllFollowings(followerId));
//    }

//    @GetMapping("/followers/{followingId}")
//    public List<FollowDTO> getAllFollowers(@PathVariable Long followingId) {
//        return followMapper.toDTOList(followService.getAllFollowers(followingId));
//    }

//    @GetMapping(value = "/{id}")
//    public FollowDTO getFollow(@PathVariable long id) {
//        return followMapper.toDTO(followService.getFollow(id));
//    }

//    @PostMapping
//    public String createFollow(@RequestBody FollowDTO followDTO) {
//        Follow follow = followMapper.toEntity(followDTO);
//        return followService.createFollow(follow);
//    }

//    @DeleteMapping(value = "/{id}")
//    public String deleteFollow(@PathVariable Long id) {
//        return followService.deleteFollow(id)
//    }

    private void storeUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    private void storeFollow(Follow follow) {
        entityManager.persist(follow);
        entityManager.flush();
    }
}
//        String pathString = "/json/actuator-health-response-200.json";
//        Path path = Paths.get(Objects.requireNonNull(ITestFollowController.class.getResource(pathString)).toURI());
//        String expectedJson = Files.readString(path);
//        System.out.println(expectedJson);
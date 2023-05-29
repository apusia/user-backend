package com.user.backend.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final ObjectMapper objectMapper;

    private final UserRepository userRepository;

//    @SneakyThrows
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String createUserBodyString){
        Optional<UserEntity> userEntity = Optional.empty();
        try {
            UserEntity value = objectMapper.readValue(createUserBodyString, UserEntity.class);
            userEntity = Optional.ofNullable(value);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Ogarnij się cieciu", HttpStatus.BAD_REQUEST);
        }

        userEntity.ifPresent(userRepository::save);

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserEntity>> readAllUsers(){
//        return new ArrayList<>(userRepository.findAll());
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}

package com.example.mongo.user.controller;

import com.example.mongo.base.exception.ObjectIdParseException;
import com.example.mongo.base.exception.UserNotFoundException;
import com.example.mongo.user.doc.UserDoc;
import com.example.mongo.user.dto.request.CreateUserRequest;
import com.example.mongo.user.dto.response.UserResponse;
import com.example.mongo.user.repository.UserRepository;
import com.example.mongo.user.routes.UserRoutes;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserApiController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = UserRoutes.CREATE)
    public UserResponse create(@RequestBody CreateUserRequest request) {
        UserDoc user = UserDoc.builder()
                .id(new ObjectId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        user = userRepository.save(user);

        return UserResponse.of(user);
    }

    @GetMapping(value = UserRoutes.SEARCH)
    public List<UserResponse> search(
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "") String query) {

        Pageable pageable = PageRequest.of(page, size);

        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<UserDoc> example = Example.of(
                UserDoc.builder().lastName(query).firstName(query).build(),
                ignoringExampleMatcher);

        Page<UserDoc> users = userRepository.findAll(example, pageable);

        return users.getContent()
                .stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }

}

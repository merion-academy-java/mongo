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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping(value = UserRoutes.BY_ID)
    public UserResponse byId(@PathVariable String id) {
        if(!ObjectId.isValid(id)) throw new ObjectIdParseException();

        UserDoc user = userRepository
                .findById(new ObjectId(id))
                .orElseThrow(UserNotFoundException::new);

        return UserResponse.of(user);
    }

}

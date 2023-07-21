package com.example.mongo.user.controller;

import com.example.mongo.user.doc.UserDoc;
import com.example.mongo.user.dto.request.CreateUserRequest;
import com.example.mongo.user.dto.response.UserResponse;
import com.example.mongo.user.repository.UserRepository;
import com.example.mongo.user.routes.UserRoutes;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = UserRoutes.CREATE)
    public UserResponse create(@RequestBody CreateUserRequest request){
        UserDoc user = UserDoc.builder()
                .id(new ObjectId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        user = userRepository.save(user);

        return UserResponse.of(user);
    }

}

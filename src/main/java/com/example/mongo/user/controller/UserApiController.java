package com.example.mongo.user.controller;

import com.example.mongo.user.doc.UserDoc;
import com.example.mongo.user.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/")
    public UserDoc test(){
        UserDoc test1 = userRepository.findByFirstName("Test");
        UserDoc userDoc = UserDoc.builder()
                .id(new ObjectId())
                .firstName("Test")
                .lastName("Test")
                .build();
        userRepository.save(userDoc);

        return userDoc;
    }

}

package com.example.mongo.user.repository;

import com.example.mongo.user.doc.UserDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserDoc, ObjectId> {
    public UserDoc findByFirstName(String firstName);
    public List<UserDoc> findByLastName(String lastName);
}

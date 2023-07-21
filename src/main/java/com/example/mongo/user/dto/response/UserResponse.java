package com.example.mongo.user.dto.response;

import com.example.mongo.user.doc.UserDoc;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserResponse {
    private String id;
    private String lastName;
    private String firstName;

    public static UserResponse of(UserDoc doc){
        return UserResponse.builder()
                .id(doc.getId().toString())
                .lastName(doc.getLastName())
                .firstName(doc.getFirstName())
                .build();
    }
}

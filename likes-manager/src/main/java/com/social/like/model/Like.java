package com.social.like.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(of = "id")
@Document
public class Like {

    @Id
    private String id;
    private String productId;
    private String userId;
    private String status;
}

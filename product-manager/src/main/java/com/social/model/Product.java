package com.social.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(of = "id")
@Document
public class Product {

    @Id
    private String id;
    private String name;
    private double price;
    private int likesCounter;
}

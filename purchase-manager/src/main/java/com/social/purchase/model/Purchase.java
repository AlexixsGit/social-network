package com.social.purchase.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(of = "id")
@Document
public class Purchase {

    @Id
    private String id;
    private String productId;
    private String userId;
    private Integer quantity;
    private float total;
}

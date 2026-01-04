package com.example.demo.features.products;

import java.util.UUID;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.demo.features.common.BaseEntity;
import com.example.demo.features.products.dto.CreateProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product extends BaseEntity {

    @Indexed(unique = true)
    private String name;

    private String description;

    private double price;

    @Field("userId")
    private UUID userId;

    public Product(CreateProduct p, UUID userId) {
        name = p.getName();
        description = p.getDescription();
        price = p.getPrice();
        this.userId = userId;
    }
}

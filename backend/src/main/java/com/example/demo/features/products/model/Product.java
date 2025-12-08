package com.example.demo.features.products.model;

import org.apache.catalina.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.features.common.BaseEntity;

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

    private User owner;

}

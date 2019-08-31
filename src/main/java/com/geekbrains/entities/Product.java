package com.geekbrains.entities;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Product {
    private long id;
    private String title;
    private int cost;
}

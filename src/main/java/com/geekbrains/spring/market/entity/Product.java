package com.geekbrains.spring.market.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Product {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "title")
    private String title;

    @NonNull
    @Column(name = "cost")
    private double cost;

}

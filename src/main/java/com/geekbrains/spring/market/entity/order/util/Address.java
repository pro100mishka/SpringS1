package com.geekbrains.spring.market.entity.order.util;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@Data
@Accessors(chain = true)
public class Address  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "address_street")
    private String addressStreet;

    @NotNull
    @Column(name = "address_home")
    private String addressHome;

    @NotNull
    @Size(min = 6,max = 10)
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull
    @Column(name = "city")
    private String city;

}

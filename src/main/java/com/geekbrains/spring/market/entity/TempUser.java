package com.geekbrains.spring.market.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "temp_user")
@Data
@Accessors(chain = true)
public class TempUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    @NotNull
    @Email
    private String email;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}

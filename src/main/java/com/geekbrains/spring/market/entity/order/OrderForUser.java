package com.geekbrains.spring.market.entity.order;

import com.geekbrains.spring.market.entity.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "order_user")
@Data
@Accessors(chain = true)
public class OrderForUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

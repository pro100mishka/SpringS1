package com.geekbrains.spring.market.entity.carts;
import com.geekbrains.spring.market.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity
@Table(name = "carts")
@Data
@Accessors(chain = true)
public class Cart  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;

}

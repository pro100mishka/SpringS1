package com.geekbrains.spring.market.entity.order;

import com.geekbrains.spring.market.entity.TempUser;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "order_temp")
@Data
@Accessors(chain = true)
public class OrderForTempUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TempUser tempUser;

}

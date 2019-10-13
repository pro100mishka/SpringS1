package com.geekbrains.spring.market.entity.carts;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;


@Entity
@Table(name = "cookie_carts")
@Data
@Accessors(chain = true)
public class CookieCart  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

}

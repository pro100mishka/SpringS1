package com.geekbrains.spring.market.repositories.order;

import com.geekbrains.spring.market.entity.order.OrderForTempUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderForTempUserRepository extends JpaRepository<OrderForTempUser,Long> {
}

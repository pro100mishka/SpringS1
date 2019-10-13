package com.geekbrains.spring.market.repositories;

import com.geekbrains.spring.market.entity.order.OrderForTempUserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderForTempUserItemsRepository extends JpaRepository<OrderForTempUserItem,Long> {
}

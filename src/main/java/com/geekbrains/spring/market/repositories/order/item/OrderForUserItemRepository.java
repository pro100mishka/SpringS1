package com.geekbrains.spring.market.repositories.order.item;

import com.geekbrains.spring.market.entity.order.item.OrderForUserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderForUserItemRepository extends JpaRepository<OrderForUserItem, Long> {
}

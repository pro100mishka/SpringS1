package com.geekbrains.spring.market.services;

import com.geekbrains.spring.market.entity.order.OrderForTempUserItem;
import com.geekbrains.spring.market.repositories.OrderForTempUserItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsService {

    private OrderForTempUserItemsRepository orderForTempUserItemsRepository;

    @Autowired
    public void setOrderForTempUserItemsRepository(OrderForTempUserItemsRepository orderForTempUserItemsRepository) {
        this.orderForTempUserItemsRepository = orderForTempUserItemsRepository;
    }

    public OrderForTempUserItem save (OrderForTempUserItem item){
        return orderForTempUserItemsRepository.save(item);
    }

}

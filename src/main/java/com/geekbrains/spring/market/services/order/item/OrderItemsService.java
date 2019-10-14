package com.geekbrains.spring.market.services.order.item;

import com.geekbrains.spring.market.entity.order.item.OrderForTempUserItem;
import com.geekbrains.spring.market.entity.order.item.OrderForUserItem;
import com.geekbrains.spring.market.repositories.order.item.OrderForTempUserItemsRepository;
import com.geekbrains.spring.market.repositories.order.item.OrderForUserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsService {

    private OrderForTempUserItemsRepository orderForTempUserItemsRepository;
    private OrderForUserItemRepository orderForUserItemRepository;

    @Autowired
    public void setOrderForUserItemRepository(OrderForUserItemRepository orderForUserItemRepository) {
        this.orderForUserItemRepository = orderForUserItemRepository;
    }

    @Autowired
    public void setOrderForTempUserItemsRepository(OrderForTempUserItemsRepository orderForTempUserItemsRepository) {
        this.orderForTempUserItemsRepository = orderForTempUserItemsRepository;
    }

    public OrderForTempUserItem save (OrderForTempUserItem item){
        return orderForTempUserItemsRepository.save(item);
    }

    public OrderForUserItem save(OrderForUserItem item){
        return orderForUserItemRepository.save(item);
    }

}

package com.geekbrains.spring.lesson4.services;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.repositories.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAllByOrderByTitleAsc();
    }

    public List<Product> findMax(){
        double cost = productRepository.findTopByOrderByCostDesc().getCost();
        return productRepository.findByCostGreaterThanEqual(cost);
    }

    public List<Product> findMin(){
        double cost = productRepository.findTopByOrderByCostAsc().getCost();
        return productRepository.findByCostLessThanEqual(cost);
    }

    public List<Product> findMaxAndMin(){
        List<Product> temp = findMax();
        temp.addAll(findMin());
        return temp;
    }

}

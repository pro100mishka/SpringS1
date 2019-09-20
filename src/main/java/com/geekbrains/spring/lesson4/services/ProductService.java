package com.geekbrains.spring.lesson4.services;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.filter.PriceFilter;
import com.geekbrains.spring.lesson4.repositories.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Data
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private double maxPrice;

    @PostConstruct
    private void init(){
        this.maxPrice = productRepository.findTopBy(Sort.by(Sort.Direction.DESC,"cost")).getCost();
        System.out.println(maxPrice);
    }

    public Page<Product> findPaginated(PageRequest pageRequest, PriceFilter priceFilter) {
        return productRepository.findAllByCostIsBetween(priceFilter.getMinPrice(),priceFilter.getMaxPrice(), pageRequest);
    }

}

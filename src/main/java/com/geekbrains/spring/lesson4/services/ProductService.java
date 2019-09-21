package com.geekbrains.spring.lesson4.services;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.repositories.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.function.Consumer;

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
    }

    public Page<Product> findAllByPagingAndFiltering(Specification<Product> specification,Pageable pageable){
        return productRepository.findAll(specification,pageable);
    }

    public Product findById(Long id){
        return productRepository.findById(id).get();
    }

    public boolean update(Product product){
        boolean temp = productRepository.save(product)!=null;
        init();
        return temp;
    }
}

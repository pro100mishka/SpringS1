package com.geekbrains.spring.market.services;

import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.repositories.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

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

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product update(Product product){
        if (maxPrice<product.getCost()) maxPrice = product.getCost();
        return productRepository.save(product);
    }

    public List<Product> findAllByFiltering(Specification<Product> specification){
        return productRepository.findAll(specification);
    }

    public List<Product> findAll(){
        return (List<Product>) productRepository.findAll();
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}

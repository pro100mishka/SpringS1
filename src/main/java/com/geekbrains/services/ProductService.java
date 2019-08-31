package com.geekbrains.services;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@NoArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public Product getById(long id){
        return productRepository.getById(id);
    }

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public boolean addProduct(Product p){
        return productRepository.addProduct(p);
    }
}

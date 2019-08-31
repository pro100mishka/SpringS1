package com.geekbrains.repositories;

import com.geekbrains.entities.Product;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class ProductRepository {
    private List<Product> productRepo;

    @PostConstruct
    public void init(){
        productRepo = new ArrayList<>();
        productRepo.add(new Product().setId(1).setTitle("Wood").setCost(399));
        productRepo.add(new Product().setId(2).setTitle("Door").setCost(4000));
        productRepo.add(new Product().setId(3).setTitle("Hammer").setCost(470));
    }

    public Product getById(long id){
        return findById(id);
    }

    private Product findById(long id){
        for (Product p: productRepo) {
            if (p.getId()==id){
                return p;
            }
        }
        return null;
    }

    public boolean addProduct(Product product){
        return productRepo.add(product);
    }

    public List<Product> getAll(){
        return productRepo;
    }

}

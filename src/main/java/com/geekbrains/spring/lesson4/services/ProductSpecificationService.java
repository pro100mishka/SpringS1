package com.geekbrains.spring.lesson4.services;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.filter.Filter;
import com.geekbrains.spring.lesson4.repositories.specifications.ProductSpecifications;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Log4j2
public class ProductSpecificationService {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Filter checkFilter(Filter filter,int size){
        log.info(filter);
        if (filter.getRange() == null) return filter.init(productService.getMaxPrice(),size);
        return filter;
    }

    public Specification<Product> getByFilter(Filter filter){
        Specification<Product> temp = Specification.where(null);

        if (filter.getRange()!=null){
            log.info("Add range: " + Arrays.toString(filter.getRange()));
            temp = temp.and(ProductSpecifications.priceGreaterThanOrEq(filter.getRange()[0]));
            temp = temp.and(ProductSpecifications.priceLesserThanOrEq(filter.getRange()[1]));
        }
        if (filter.getWord()!=null && filter.getWord().length()>0){
            log.info("Add like word: " + filter.getWord());
            temp = temp.and(ProductSpecifications.titleContains(filter.getWord()));
        }
        return temp;
    }

}

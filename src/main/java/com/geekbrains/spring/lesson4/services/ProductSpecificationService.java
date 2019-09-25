package com.geekbrains.spring.lesson4.services;

import com.geekbrains.spring.lesson4.entity.Product;
import com.geekbrains.spring.lesson4.filter.ProductFilterService;
import com.geekbrains.spring.lesson4.repositories.specifications.ProductSpecifications;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
@Log4j2
public class ProductSpecificationService {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductFilterService checkFilter(HttpServletRequest request){
        System.out.println(Arrays.toString(request.getParameterMap().get("range")));
        return null;
    }

    public Specification<Product> getByFilter(ProductFilterService productFilterService){
        Specification<Product> temp = Specification.where(null);

        if (productFilterService.getRange()!=null){
            log.info("Add range: " + Arrays.toString(productFilterService.getRange()));
            temp = temp.and(ProductSpecifications.priceGreaterThanOrEq(productFilterService.getRange()[0]));
            temp = temp.and(ProductSpecifications.priceLesserThanOrEq(productFilterService.getRange()[1]));
        }
        if (productFilterService.getWord()!=null && productFilterService.getWord().length()>0){
            log.info("Add like word: " + productFilterService.getWord());
            temp = temp.and(ProductSpecifications.titleContains(productFilterService.getWord()));
        }
        return temp;
    }

}

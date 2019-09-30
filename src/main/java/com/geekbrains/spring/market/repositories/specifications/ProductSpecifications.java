package com.geekbrains.spring.market.repositories.specifications;

import com.geekbrains.spring.market.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> titleContains(String word){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"),"%"+word+"%");
    }
    public static Specification<Product> priceGreaterThanOrEq(double value){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("cost"),value);
    }
    public static Specification<Product> priceLesserThanOrEq(double value){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("cost"),value);
    }
}

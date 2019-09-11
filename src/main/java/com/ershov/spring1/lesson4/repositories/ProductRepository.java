package com.ershov.spring1.lesson4.repositories;

import com.ershov.spring1.lesson4.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByTitleAsc();
    Product findTopByOrderByCostDesc();
    Product findTopByOrderByCostAsc();
    List<Product> findByCostGreaterThanEqual(double cost);
    List<Product> findByCostLessThanEqual (double cost);
}

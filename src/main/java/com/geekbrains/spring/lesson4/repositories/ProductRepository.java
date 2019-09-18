package com.geekbrains.spring.lesson4.repositories;

import com.geekbrains.spring.lesson4.entity.Product;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAll();
    Page<Product> findAllByCostIsBetween(double minPrice, double maxPrice, Pageable pageable);
    Product findTopBy(Sort cost);
}

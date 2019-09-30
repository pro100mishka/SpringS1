package com.geekbrains.spring.market.services.filter_services;

import com.geekbrains.spring.market.entity.Product;
import com.geekbrains.spring.market.repositories.specifications.ProductSpecifications;
import com.geekbrains.spring.market.services.ProductService;
import com.geekbrains.spring.market.services.filter_services.filter.FilterForPage;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;


@Log4j2
@Service
@Accessors(chain = true)
public class ProductFilterAndPageService {

    private ProductService productService;
    @Getter
    private FilterForPage filter;


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    private void init(){
        filter = new FilterForPage(productService.getMaxPrice());
    }

    public Specification<Product> getSpecification(Optional<String> word,Optional<Double[]> range){
        updateDefaultRange();
        String currentWord = word.orElse(null);
        Double[] currentRange = range.orElse(null);
        Specification<Product> temp = Specification.where(null);
        if (currentRange!=null) {
            log.info("Add range: " + Arrays.toString(currentRange));
            temp = temp.and(ProductSpecifications.priceGreaterThanOrEq(currentRange[0]));
            temp = temp.and(ProductSpecifications.priceLesserThanOrEq(currentRange[1]));
        }
        filter.setRange(currentRange);
        if (currentWord!=null && !currentWord.isEmpty()) {
            log.info("Add like word: " + currentWord);
            temp = temp.and(ProductSpecifications.titleContains(currentWord));
        }
        filter.setWord(currentWord);
        return temp;
    }

    public PageRequest getPageRequest(Optional<Integer> page,Optional<Integer> size){
        int currentPage = page.orElse(1);
        int currentSize = size.orElse(5);
        return PageRequest.of(currentPage-1, currentSize, Sort.by(Sort.Direction.DESC,"cost"));
    }

    private void updateDefaultRange(){
        filter.getDefaultRange()[1] = productService.getMaxPrice();
    }

}

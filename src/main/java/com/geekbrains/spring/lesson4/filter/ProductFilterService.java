package com.geekbrains.spring.lesson4.filter;

import com.geekbrains.spring.lesson4.repositories.specifications.ProductSpecifications;
import com.geekbrains.spring.lesson4.services.ProductService;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;


@Log4j2
@Service
@Getter
public class ProductFilterService {

    private ProductService productService;

    private Double [] range;
    private String word;
    private Double [] defaultRange;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public ProductFilterService checkFilter(HttpServletRequest request){
        this.defaultRange = initDefaultRange(defaultRange);
        if (checkParameter(request,"range")) {
            log.info("Add range: " + request.getParameter("range"));

        }

        System.out.println(Arrays.toString(request.getParameterMap().get("range")));
        return null;
    }

    private Double[] initDefaultRange(Double[] range){
        if (range==null) return new Double[]{0.0,productService.getMaxPrice()};
        range[1] = productService.getMaxPrice();
        return range;
    }

    private boolean checkParameter(HttpServletRequest request, String parameterName){
        return request.getParameter(parameterName) != null && !request.getParameter(parameterName).isEmpty();
    }

}

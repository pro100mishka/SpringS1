package com.geekbrains.spring.lesson4.filter;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PriceFilter {

    Double minPrice;
    Double maxPrice;

}

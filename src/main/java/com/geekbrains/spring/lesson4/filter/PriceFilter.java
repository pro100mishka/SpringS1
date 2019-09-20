package com.geekbrains.spring.lesson4.filter;

import lombok.*;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class PriceFilter {

    public PriceFilter init(){
        this.range = new Double[]{defaultMin,defaultMax};
        return this;
    }

    private Double[] range;
    private Double defaultMin = 0.0;
    private Double defaultMax = 7000.0;
}

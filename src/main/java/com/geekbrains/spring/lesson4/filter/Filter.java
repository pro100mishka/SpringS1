package com.geekbrains.spring.lesson4.filter;

import lombok.*;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Filter {

    private String word;
    private Double[] range;
    private Double defaultMin;
    private Double defaultMax;
    private int size; //это конечно колхоз

    public Filter init(Double defaultMax){
        this.defaultMax = defaultMax;
        this.defaultMin = 0.0;
        this.range = new Double[]{defaultMin,defaultMax};
        return this;
    }
}

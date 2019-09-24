package com.geekbrains.spring.lesson4.filter;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;


@Data
@Accessors(chain = true)
@Log4j2
@NoArgsConstructor
public class Filter {

    private String word;
    private Double[] range;
    private Double defaultMin;
    private Double defaultMax;
    private int size; //это конечно колхоз

    public Filter init(Double defaultMax,int size){
        log.info("Filter Initialization. Filter before: "+this);
        this.defaultMax = defaultMax;
        this.defaultMin = 0.0;
        this.range = new Double[]{defaultMin,defaultMax};
        this.size = size;
        log.info("Filter Initialization. Filter after: "+this);
        return this;
    }
}

package com.geekbrains.spring.market.services.filter_services.filter;

import lombok.Data;

@Data
public class FilterForPage {

    private StringBuilder stringBuilder;
    private Double [] range;
    private String word;
    private Double [] defaultRange;

    public FilterForPage(Double max) {
        defaultRange = new Double[]{0.0, max};
        stringBuilder = new StringBuilder();
    }

    @Override
    public String toString() {
        stringBuilder.setLength(0);
        if (range!=null){
            stringBuilder.append("&range=");
            for (int i = 0; i <range.length ; i++) {
                if (i>0) stringBuilder.append(",");
                stringBuilder.append(range[i]);
            }
        }
        if (word!=null) stringBuilder.append("&word=").append(word);
        return stringBuilder.toString();
    }
}

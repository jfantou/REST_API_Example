package com.jfantou.demo.utils;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class FilterSomeBean {

        public static FilterProvider getSomeBeanFilter(Set<String> listField){
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(listField);
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
            return filterProvider;
        }
}

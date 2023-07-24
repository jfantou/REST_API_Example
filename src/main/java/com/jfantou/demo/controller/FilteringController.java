package com.jfantou.demo.controller;

import com.jfantou.demo.model.SomeBean;
import com.jfantou.demo.utils.FilterSomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
public class FilteringController {

    @GetMapping("filtering")
    public MappingJacksonValue filter(){
        SomeBean someBean = SomeBean.builder().field1("test1").field2("test2").field3("test3").build();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        mappingJacksonValue.setFilters(FilterSomeBean.getSomeBeanFilter(Set.of("field1")));
        return mappingJacksonValue;

    }

    @GetMapping("filtering-list")
    public MappingJacksonValue filterList(){
        SomeBean someBean = SomeBean.builder().field1("test1").field2("test2").field3("test3").build();
        SomeBean someBean2 = SomeBean.builder().field1("test1").field2("test2").field3("test3").build();
        List<SomeBean> list = Arrays.asList(someBean, someBean2);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        mappingJacksonValue.setFilters(FilterSomeBean.getSomeBeanFilter(Set.of("field2")));
        return mappingJacksonValue;

    }
}

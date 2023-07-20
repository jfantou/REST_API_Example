package com.jfantou.demo.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@Getter
@Setter
@Builder
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    private String field2;
    private String field3;
}

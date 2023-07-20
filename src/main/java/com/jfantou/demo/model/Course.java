package com.jfantou.demo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    private Integer id;

    @Size(min=2, message="name should be superiror at 2 letters")
    private String name;

    @Min(value = 1, message = "The duration of the course should be superior at 0")
    private int duration;

    private String type;
}

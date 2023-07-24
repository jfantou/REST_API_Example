package com.jfantou.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="program_details")
public class Program {

    @Id
    @GeneratedValue
    @Column(name="program_id")
    private Integer id;

    @Size(min=2, message="name should be superiror at 2 letters")
    private String name;

    @Min(value = 1, message = "The duration of the course should be superior at 0")
    private int duration;

    private String type;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Course> courses;

}

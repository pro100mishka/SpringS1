package com.geekbrains.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors (chain = true)
public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private String[] programmingLanguages;
}

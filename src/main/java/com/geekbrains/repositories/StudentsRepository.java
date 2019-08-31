package com.geekbrains.repositories;

import com.geekbrains.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentsRepository {
    public Student findOneById(Long id) {
        return new Student().setFirstName("test" + id).setLastName("test" + id);
    }
}

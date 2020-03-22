package lab2testing.repository;

import lab2testing.domain.Student;
import lab2testing.validation.*;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}


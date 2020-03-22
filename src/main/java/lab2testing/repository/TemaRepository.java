package lab2testing.repository;

import lab2testing.domain.Tema;
import lab2testing.validation.*;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}


package proj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proj.domain.Pizza;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza,Long> {
        Pizza findByName(String name);
}

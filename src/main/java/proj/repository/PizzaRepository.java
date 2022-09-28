package proj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import proj.domain.Pizza;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza,Long> {

}

package proj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proj.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
        Product findProductByProdName(String name);
}

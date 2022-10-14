package proj.repository;

import org.springframework.data.repository.CrudRepository;
import proj.domain.ProductInStock;


public interface StockRepository extends CrudRepository<ProductInStock, Long> {
        ProductInStock findProductInStockByNameOfProd(String name);
}

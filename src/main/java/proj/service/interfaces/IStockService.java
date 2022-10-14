package proj.service.interfaces;

import org.springframework.cache.annotation.Cacheable;
import proj.domain.DTO.ProductInStockPojo;
import proj.domain.ProductInStock;
import java.util.Set;

public interface IStockService {
    void createProductInStock(ProductInStock product);
    ProductInStockPojo getProductFromStock(long id);
    Set<ProductInStockPojo> getAllProductFromStock();
    void updateProductInStock(ProductInStock product);
    void deleteProductFromStock(ProductInStock product);
    void deleteProductFromStockById(long id);

    @Cacheable("stockCache")
    void clearCache();
}

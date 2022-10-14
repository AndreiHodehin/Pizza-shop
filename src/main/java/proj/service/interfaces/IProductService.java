package proj.service.interfaces;

import proj.domain.DTO.ProductPojo;
import proj.domain.Product;
import java.util.SortedSet;

public interface IProductService {
    ProductPojo createProduct(Product user);

    ProductPojo getProduct(long id);

    ProductPojo updateProduct(Product user);
    void deleteProduct(long id);
    SortedSet<ProductPojo> getAllProduct();
    ProductPojo findProductByName(String name);
    void clearCache();
}

package proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.domain.DTO.ProductPojo;
import proj.domain.Product;
import proj.exceptions.EmptyDataException;
import proj.repository.ProductRepository;
import proj.service.interfaces.IProductService;
import proj.utils.Convertor;

import java.util.*;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductService productService;
    private final ProductRepository productRepository;
    private final Convertor convertor;

    @Autowired
    public ProductService (ProductRepository productRepository, Convertor convertor) {
        this.productRepository = productRepository;
        this.convertor = convertor;
    }
    @Override
    @Transactional
    public ProductPojo createProduct(Product product) {
        productService.clearCache();
        productRepository.save(product);
        return convertor.productToPojo(product);
    }

    @Override
    @Transactional
    public ProductPojo getProduct(long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()) {
            return convertor.productToPojo(product.get());
        }else {
            throw new EmptyDataException("unable to get product");
        }
    }
    @Override
    @Transactional
    public ProductPojo findProductByName(String name){
        return convertor.productToPojo(productRepository.findProductByProdName(name));
    }

    @Override
    @Transactional
    @Cacheable(value = "productCache")
    public SortedSet<ProductPojo> getAllProduct() {
        SortedSet<ProductPojo> set = new TreeSet<>();
        productRepository.findAll().forEach(p->set.add(convertor.productToPojo(p)));
        return set;
    }

    @Override
    @Transactional
    public ProductPojo updateProduct(Product product) {
        productService.clearCache();
        Optional<Product> old = productRepository.findById(product.getId());
        if(old.isPresent()) {
            Product target = old.get();
            if(product.getProdName() == null){
                target.setProdName(product.getProdName());
            }
            target.setAmount(product.getAmount());
            target.setPizzaList(product.getPizzaList());
            target.setUnit(product.getUnit());
            productRepository.save(target);
        }
        return convertor.productToPojo(product);
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        productService.clearCache();
        productRepository.deleteById(id);
    }

    @CacheEvict(value = "productCache")
    public void clearCache() {
        System.out.println("Product cache cleared");
    }

}

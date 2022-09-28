package proj.service;

import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("in create product");
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
    public SortedSet<ProductPojo> getAllProduct() {
        SortedSet<ProductPojo> set = new TreeSet<>();
        productRepository.findAll().forEach(p->set.add(convertor.productToPojo(p)));
        return set;
    }

    @Override
    @Transactional
    public ProductPojo updateProduct(Product product) {
        Optional<Product> old = productRepository.findById(product.getId());
        if(old.isPresent()) {
            Product target = old.get();
            if(product.getProdName() == null){
                target.setProdName(product.getProdName());
            }
            target.setAmountInUnit(product.getAmountInUnit());
            target.setPizzaList(product.getPizzaList());
            target.setUnit(product.getUnit());
            target.setTotalStock(product.getTotalStock());
            productRepository.save(target);
        }
        return convertor.productToPojo(product);
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }


}

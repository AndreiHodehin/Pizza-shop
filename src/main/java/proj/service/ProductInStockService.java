package proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.domain.DTO.ProductInStockPojo;
import proj.domain.ProductInStock;
import proj.exceptions.EmptyDataException;
import proj.repository.StockRepository;
import proj.service.interfaces.IStockService;
import proj.utils.Convertor;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
public class ProductInStockService implements IStockService {
    @Autowired
    private IStockService stockService;
    private final StockRepository stockRepository;
    private final Convertor convertor;
    @Autowired
    public ProductInStockService (StockRepository stockRepository, Convertor convertor){
        this.stockRepository = stockRepository;
        this.convertor = convertor;
    }
    @Override
    @Transactional
    public void createProductInStock(ProductInStock product) {
        stockService.clearCache();
        stockRepository.save(product);
    }

    @Override
    @Transactional
    public ProductInStockPojo getProductFromStock(long id) {
        Optional<ProductInStock> product = stockRepository.findById(id);

        if(product.isPresent()) {
            return convertor.stockProductToPojo(product.get());
        }else {
            throw new EmptyDataException("unable to get product in stock");
        }
    }

    @Override
    @Transactional
    @Cacheable(value = "stockCache")
    public Set<ProductInStockPojo> getAllProductFromStock() {
        Set<ProductInStockPojo> pojoSet = new TreeSet<>();
        stockRepository.findAll().forEach(s->pojoSet.add(convertor.stockProductToPojo(s)));
        return pojoSet;
    }

    @Override
    @Transactional
    public void updateProductInStock(ProductInStock product) {
        stockService.clearCache();
        Optional<ProductInStock> optional = stockRepository.findById(product.getId());

        if(optional.isPresent()) {
            ProductInStock target = optional.get();
            target.setNameOfProd(product.getNameOfProd());
            target.setUnit(product.getUnit());
            target.setTotalStock(product.getTotalStock());
            stockRepository.save(target);
        }
    }

    @Override
    @Transactional
    public void deleteProductFromStock(ProductInStock product) {
        stockService.clearCache();
        stockRepository.delete(product);
    }

    @Override
    @Transactional
    public void deleteProductFromStockById(long id) {
        stockService.clearCache();
        stockRepository.deleteById(id);
    }

    @Override
    @CacheEvict("stockCache")
    public void clearCache() {
        System.out.println("Stock cache cleared");
    }
}

package proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import proj.domain.DTO.PizzaPojo;
import proj.domain.Pizza;
import proj.domain.Product;
import proj.exceptions.EmptyDataException;
import proj.exceptions.IncorrectDataEnteredException;
import proj.repository.PizzaRepository;
import proj.repository.ProductRepository;
import proj.repository.StockRepository;
import proj.service.interfaces.IPizzaService;
import proj.utils.Convertor;

import java.util.*;

@Component
public class PizzaService implements IPizzaService {
    @Autowired
    private IPizzaService pizzaService;
    private final PizzaRepository pizzaRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final Convertor convertor;
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository,
                        ProductRepository productRepository,
                        StockRepository stockRepository,
                        Convertor convertor) {
        this.pizzaRepository = pizzaRepository;
        this.convertor = convertor;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }
    @Override
    @Transactional
    public PizzaPojo createPizza(Pizza pizza) {
        pizzaService.clearCache();
        pizzaRepository.save(pizza);
        return convertor.pizzaToPojo(pizza);
    }
    @Override
    @Transactional
    public PizzaPojo createPizza(Pizza pizza,String[] products,String[] amounts) {
        pizzaService.clearCache();

        checkCorrectValuesAmountOfProduct(products,amounts);

        for (int i = 0 ,j = 0; i < products.length; i++, j++) {
            Product newProduct = new Product();
            newProduct.setProdName(products[i]);
            while (amounts[j].isBlank()) {
                j++;
            }
            if(!amounts[j].isBlank()){
                double d = Double.parseDouble(amounts[j]);
                newProduct.setAmount(d);
                System.out.println(j);
            }

            newProduct.setUnit(stockRepository.findProductInStockByNameOfProd(products[i]).getUnit());
            productRepository.save(newProduct);
            pizza.getProductList().add(newProduct);

        }
        pizzaRepository.save(pizza);
        return convertor.pizzaToPojo(pizza);
    }

    @Override
    @Transactional
    public PizzaPojo getPizza(long id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);

        if(pizza.isPresent()) {
            return convertor.pizzaToPojo(pizza.get());
        }else {
            throw new EmptyDataException("unable to get pizza");
        }
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "pizzaCache")
    public SortedSet<PizzaPojo> getAllPizza() {
        SortedSet<PizzaPojo> set = new TreeSet<>();
        pizzaRepository.findAll().forEach(e->set.add(convertor.pizzaToPojo(e)));
        return set;
    }

    @Override
    @Transactional
    public PizzaPojo updatePizza(Pizza pizza) {
        pizzaService.clearCache();
        Optional<Pizza> old = pizzaRepository.findById(pizza.getId());
        if (old.isPresent()) {
            Pizza target = old.get();

            if(pizza.getName()!= null){
            target.setName(pizza.getName());}

            target.setProductList(pizza.getProductList());
            pizzaRepository.save(target);
            return convertor.pizzaToPojo(target);
        } else {
            throw new EmptyDataException("no such element");
        }
    }
    @Override
    @Transactional
    public PizzaPojo updatePizza(long id,String[] products,String[] amounts) {
        pizzaService.clearCache();

        checkCorrectValuesAmountOfProduct(products,amounts);

        Optional<Pizza> old = pizzaRepository.findById(id);
        if (old.isPresent()) {
            Pizza target = old.get();
            target.getProductList().clear();

            SortedSet<Product> productSet = new TreeSet<>();
            for (int i = 0, j = 0 ; i < products.length; i++, j++) {
                Product product = new Product();
                product.setProdName(products[i]);
                product.setUnit(stockRepository.findProductInStockByNameOfProd(products[i]).getUnit());
                product.addPizza(target);
                while (amounts[j].isBlank()) {
                    j++;
                }
                if(!amounts[j].isBlank()){
                    double d = Double.parseDouble(amounts[j]);
                    product.setAmount(d);
                    System.out.println(j);
                }
                productSet.add(product);
                productRepository.save(product);
            }
            target.setProductList(productSet);

            pizzaRepository.save(target);
            return convertor.pizzaToPojo(target);
        } else {
            throw new EmptyDataException("no such element");
        }
    }
    @Override
    @Transactional
    public void deletePizza(long id) {
        pizzaService.clearCache();
        pizzaRepository.deleteById(id);
    }

    @Override
    public Pizza findByName(String name) {
        return pizzaRepository.findByName(name);
    }

    @Override
    @CacheEvict(value = "pizzaCache")
    public void clearCache(){
        System.out.println("Pizza cache cleared");
    }

    private void checkCorrectValuesAmountOfProduct(String[] products,String[] amounts) {
        int numsOfAmount = 0;
        for (String s:amounts) {
            if (!s.isBlank()){
                numsOfAmount++;
            }
        }
        if(products.length != numsOfAmount){
            throw new IncorrectDataEnteredException("enter amount for each chosen product");
        }
    }
}

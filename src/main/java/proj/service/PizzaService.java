package proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import proj.domain.DTO.PizzaPojo;
import proj.domain.Pizza;
import proj.domain.Product;
import proj.exceptions.EmptyDataException;
import proj.repository.PizzaRepository;
import proj.repository.ProductRepository;
import proj.service.interfaces.IPizzaService;
import proj.utils.Convertor;

import java.util.*;

@Component
public class PizzaService implements IPizzaService {
    private final PizzaRepository pizzaRepository;
    private final ProductRepository productRepository;
    private final Convertor convertor;
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository,
                        ProductRepository productRepository,
                        Convertor convertor) {
        this.pizzaRepository = pizzaRepository;
        this.convertor = convertor;
        this.productRepository = productRepository;
    }
    @Override
    @Transactional
    public PizzaPojo createPizza(Pizza pizza,String[] products) {

        SortedSet<Product> productSet = pizza.getProductList();

        for (String str:products) {
            Product product = productRepository.findProductByProdName(str);
            if(product != null) {
                productSet.add(product);
            }
        }
        pizza.setProductList(productSet);
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
            throw new EmptyDataException("unable to get user");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public SortedSet<PizzaPojo> getAllPizza() {
        SortedSet<PizzaPojo> set = new TreeSet<>();
        pizzaRepository.findAll().forEach(e->set.add(convertor.pizzaToPojo(e)));
        return set;
    }

    @Override
    @Transactional
    public PizzaPojo updatePizza(Pizza pizza) {
        Optional<Pizza> old = pizzaRepository.findById(pizza.getId());
        if (old.isPresent()) {
            Pizza target = old.get();

            if(pizza.getName()!=null){
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
    public void deletePizza(long id) {
        pizzaRepository.deleteById(id);
    }


}

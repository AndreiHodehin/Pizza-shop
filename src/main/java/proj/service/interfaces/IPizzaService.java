package proj.service.interfaces;

import org.springframework.cache.annotation.CacheEvict;
import proj.domain.DTO.PizzaPojo;
import proj.domain.Pizza;

import java.util.SortedSet;

public interface IPizzaService {
    PizzaPojo createPizza(Pizza user);

    PizzaPojo getPizza(long id);
    PizzaPojo updatePizza(Pizza user);
    void deletePizza(long id);
    SortedSet<PizzaPojo> getAllPizza();
    PizzaPojo createPizza(Pizza pizza,String[] products,String[] amounts);
    PizzaPojo updatePizza(long id,String[] products,String[] amounts);

    Pizza findByName(String name);

    @CacheEvict
    void clearCache();
}

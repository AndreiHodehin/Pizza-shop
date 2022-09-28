package proj.service.interfaces;

import proj.domain.DTO.PizzaPojo;
import proj.domain.Pizza;

import java.util.Set;
import java.util.SortedSet;

public interface IPizzaService {
    PizzaPojo createPizza(Pizza user,String[] products);

    PizzaPojo getPizza(long id);

    PizzaPojo updatePizza(Pizza user);
    void deletePizza(long id);

    SortedSet<PizzaPojo> getAllPizza();
}

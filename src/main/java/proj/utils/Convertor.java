package proj.utils;

import org.springframework.stereotype.Component;
import proj.domain.DTO.PizzaPojo;
import proj.domain.DTO.ProductPojo;
import proj.domain.Pizza;
import proj.domain.Product;

import java.util.stream.Collectors;

@Component
public class Convertor {

    public ProductPojo productToPojo(Product product){
        ProductPojo pojo = new ProductPojo();
        pojo.setId(product.getId());
        pojo.setMinAmount(product.getAmountInUnit());
        pojo.setPojoSet(product.getPizzaList().stream().map(Pizza::getId).collect(Collectors.toSet()));
        pojo.setProdName(product.getProdName());
        pojo.setUnit(product.getUnit());
        pojo.setTotalStock(product.getTotalStock());
        return pojo;
    }
    public PizzaPojo pizzaToPojo(Pizza pizza){
        PizzaPojo pojo = new PizzaPojo();
        pojo.setId(pizza.getId());
        pojo.setName(pizza.getName());
        pojo.setProductList(pizza.getProductList().stream().map(this::productToPojo).collect(Collectors.toSet()));
        return pojo;
    }
}

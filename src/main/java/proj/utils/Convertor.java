package proj.utils;

import org.springframework.stereotype.Component;
import proj.domain.*;
import proj.domain.DTO.*;

import java.util.stream.Collectors;

@Component
public class Convertor {

    public ProductPojo productToPojo(Product product){
        ProductPojo pojo = new ProductPojo();
        pojo.setId(product.getId());
        pojo.setAmount(product.getAmount());
        pojo.setPizzaList(product.getPizzaList().stream().map(Pizza::getId).collect(Collectors.toSet()));
        pojo.setProdName(product.getProdName());
        pojo.setUnit(product.getUnit());
        return pojo;
    }
    public PizzaPojo pizzaToPojo(Pizza pizza){
        PizzaPojo pojo = new PizzaPojo();
        pojo.setId(pizza.getId());
        pojo.setName(pizza.getName());
        pojo.setProductList(pizza.getProductList().stream().map(this::productToPojo).collect(Collectors.toSet()));
        pojo.setCookingTimeInMin(pizza.getCookingTimeInMin());
        return pojo;
    }

    public ProductInStockPojo stockProductToPojo(ProductInStock productInStock) {
        ProductInStockPojo pojo = new ProductInStockPojo();
        pojo.setId(productInStock.getId());
        pojo.setNameOfProd(productInStock.getNameOfProd());
        pojo.setUnit(productInStock.getUnit());
        pojo.setTotalStock(productInStock.getTotalStock());
        return pojo;
    }

    public UserPojo userToPojo(User user) {
        UserPojo pojo = new UserPojo();
        pojo.setId(user.getId());
        pojo.setUsername(user.getUsername());
        pojo.setAddress(user.getAddress());
        pojo.setEmail(user.getEmail());
        pojo.setPassword(user.getPassword());
        pojo.setPhoneNumber(user.getPhoneNumber());
        pojo.setSurname(user.getSurname());
        pojo.setRole(user.getRole());
        return pojo;
    }
    public OrderPojo orderToPojo(Order order) {
        OrderPojo pojo = new OrderPojo();
        pojo.setId(order.getId());
        pojo.setUser(order.getUser());
        pojo.setCreatedTime(order.getCreatedTime());
        pojo.setOrderedPizza(order.getOrderedPizza().stream().map(this::pizzaToPojo).collect(Collectors.toList()));
        return pojo;
    }
}

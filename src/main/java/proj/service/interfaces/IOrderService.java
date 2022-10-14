package proj.service.interfaces;

import proj.domain.DTO.OrderPojo;
import proj.domain.Order;
import proj.domain.Pizza;

import java.util.List;

public interface IOrderService {
    OrderPojo findOrderById(long id);
    List<OrderPojo> findAllOrder();
    List<OrderPojo> findAllOrderOfUserByUsername(String username);
    void createOrder(Order order, List<Pizza> pizza, String username);
    void updateOrder(Order order);
    void deleteOrder(long id);
    public List<OrderPojo> findOrdersInProcessByUsername(List<OrderPojo> orderList);

}

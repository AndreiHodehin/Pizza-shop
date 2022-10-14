package proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.domain.DTO.OrderPojo;
import proj.domain.DTO.PizzaPojo;
import proj.domain.Order;
import proj.domain.Pizza;
import proj.domain.User;
import proj.exceptions.EmptyDataException;
import proj.repository.OrderRepository;
import proj.repository.UserRepository;
import proj.service.interfaces.IOrderService;
import proj.utils.Convertor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final Convertor convertor;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        Convertor convertor) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.convertor = convertor;
    }
    @Override
    @Transactional
    public OrderPojo findOrderById(long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if(optional.isPresent()){
        return convertor.orderToPojo(optional.get());
        } else {
            throw new EmptyDataException("no user");
        }
    }

    @Override
    @Transactional
    public List<OrderPojo> findAllOrder() {
        List<OrderPojo> list = new ArrayList<>();
        orderRepository.findAll().forEach(o->list.add(convertor.orderToPojo(o)));
        return list;
    }

    @Override
    @Transactional
    public List<OrderPojo> findAllOrderOfUserByUsername(String username) {
        List<OrderPojo> ordersPojo = new ArrayList<>();
        Optional<User> optional = userRepository.findUserByUsername(username);

        if (optional.isPresent()) {
            List<Order> orders = orderRepository.findAllByUser(optional.get());
            orders.forEach(o->ordersPojo.add(convertor.orderToPojo(o)));
        }
        return ordersPojo;
    }

    @Override
    @Transactional
    public void createOrder(Order order, List<Pizza> pizza, String username) {

        Optional<User> optional = userRepository.findUserByUsername(username);

        if (optional.isPresent()) {
            pizza.forEach(order::addPizza);
            order.setUser(optional.get());
            LocalDateTime time = LocalDateTime.now();
            order.setCreatedTime(time);

            orderRepository.save(order);
        } else {
            throw new EmptyDataException("no user ");
        }
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {

    }

    @Override
    @Transactional
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }


    @Override
    @Transactional
    public List<OrderPojo> findOrdersInProcessByUsername(List<OrderPojo> orderList){
        List<OrderPojo> inProcessOrder = new ArrayList<>();
        for (OrderPojo order:orderList) {
            LocalDateTime now = LocalDateTime.now();
            List<PizzaPojo> listPizzaPojo= order.getOrderedPizza();

            for (PizzaPojo p:listPizzaPojo) {
                if(order.getCreatedTime().plusMinutes(p.getCookingTimeInMin()).isBefore(now)) {
                    order.setPrepared(true);
                } else {
                    order.setPrepared(false);
                    if(!inProcessOrder.contains(order)){
                        inProcessOrder.add(order);}
                }
            }
        }
        return inProcessOrder;
    }
}

package proj.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proj.domain.DTO.OrderPojo;
import proj.domain.DTO.UserPojo;
import proj.domain.Order;
import proj.domain.Pizza;
import proj.domain.Role;
import proj.domain.User;
import proj.exceptions.IncorrectDataEnteredException;
import proj.service.interfaces.IOrderService;
import proj.service.interfaces.IPizzaService;
import proj.service.interfaces.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final IPizzaService pizzaService;
    private final IOrderService orderService;
    private final IUserService userService;

    @Autowired
    public UserController(IPizzaService pizzaService, IOrderService orderService,IUserService userService) {
        this.pizzaService = pizzaService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Transactional
    public void setStandardViewAndPizzaList(ModelAndView modelAndView) {
        modelAndView.addObject("allPizza",pizzaService.getAllPizza());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("orderedPizza",userService.findAllPizzaOfUserByUsername(authentication.getName()));


        List<OrderPojo> orderList = orderService.findAllOrderOfUserByUsername(authentication.getName());
        List<OrderPojo> inProcessOrder = orderService.findOrdersInProcessByUsername(orderList);

        modelAndView.addObject("orderUsers",orderList);
        modelAndView.addObject("orderInProcess",inProcessOrder);
        modelAndView.setViewName("user.jsp");
    }

    @GetMapping(value = "/")
    public ModelAndView userMain(ModelAndView modelAndView){
        setStandardViewAndPizzaList(modelAndView);
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView registerUser(@ModelAttribute("user") @Valid User user, ModelAndView modelAndView) {
        user.addRole(Role.ROLE_USER);
        if(userService.getUserByUsername(user.getUsername()) != null){
            throw new IncorrectDataEnteredException("User with this name are exist");
        }
        userService.createUser(user);
        modelAndView.setViewName("/login.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/order")
    @Transactional
    public ModelAndView orderPizza(HttpServletRequest request, ModelAndView modelAndView, Order order) {
        userService.clearCache();

        String userName = request.getUserPrincipal().getName();

        String[] pizzaNames = request.getParameterValues("pizzaName");
        List<Pizza> pizzas = Arrays.stream(pizzaNames).map(pizzaService::findByName).collect(Collectors.toList());

        orderService.createOrder(order,pizzas,userName);
        setStandardViewAndPizzaList(modelAndView);
        return modelAndView;
    }

    @PostMapping(value = "/info")
    public ModelAndView insertInfo(ModelAndView modelAndView, @ModelAttribute("user") @Valid User user, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();

        modelAndView.addObject("userInfo",userService.getUserByUsername(username));

        user.setUsername(username);
        userService.fillInfoAboutUser(user);
        modelAndView.setViewName("/main.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/infoUser")
    public ModelAndView getInfo(ModelAndView modelAndView) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        modelAndView.addObject("userInfo",userService.getUserByUsername(username));
        modelAndView.setViewName("info.jsp");

        return modelAndView;
    }

    @GetMapping(value = "/users")
    public ModelAndView allUserList(ModelAndView modelAndView) {
        modelAndView.addObject("userList",userService.findAllUser());
        modelAndView.setViewName("userList.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/remove/{id}")
    public ModelAndView removeUser(@PathVariable long id, ModelAndView modelAndView) {
        userService.deleteUser(id);
        modelAndView.addObject("userList",userService.findAllUser());
        modelAndView.setViewName("redirect:userList.jsp");
        return modelAndView;
    }
}

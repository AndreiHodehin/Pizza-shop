package proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proj.domain.DTO.PizzaPojo;
import proj.domain.DTO.ProductPojo;
import proj.domain.Pizza;
import proj.service.PizzaService;
import proj.service.interfaces.IPizzaService;
import proj.service.interfaces.IProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Set;
import java.util.SortedSet;

@Controller
@RequestMapping(value = "/pizza")
public class PizzaController {

    private final IPizzaService pizzaService;
    private final IProductService productService;
    @Autowired
    public PizzaController(IPizzaService service,IProductService productService) {
        this.pizzaService = service;
        this.productService = productService;
    }

    private void setStandardViewAndAllPizza(ModelAndView modelAndView){
        SortedSet<PizzaPojo> pizzaList = pizzaService.getAllPizza();
        SortedSet<ProductPojo> productList = productService.getAllProduct();
        modelAndView.addObject("allProduct",productList);
        modelAndView.addObject("allPizza",pizzaList);
        modelAndView.setViewName("pizza.jsp");
    }

    @GetMapping(value = "/")
    public ModelAndView pizzaMain(ModelAndView modelAndView) {
        setStandardViewAndAllPizza(modelAndView);
        return modelAndView;
    }

    @PostMapping(value = "/createPizza")
    public ModelAndView createPizza(@ModelAttribute("pizza") Pizza pizza,ModelAndView modelAndView,HttpServletRequest request) {
        String[] str = request.getParameterValues("prod");
        pizzaService.createPizza(pizza,str);
        setStandardViewAndAllPizza(modelAndView);
        return modelAndView;
    }

    @GetMapping(value = "/remove/{id}")
    public ModelAndView remove(@PathVariable long id, ModelAndView modelAndView) {
        pizzaService.deletePizza(id);
        modelAndView.addObject("allPizza", pizzaService.getAllPizza());
        modelAndView.setViewName("redirect:/pizza/");
        return modelAndView;
    }

    @PostMapping(value = "/update")
    public ModelAndView update(@ModelAttribute("pizza") Pizza pizza, ModelAndView modelAndView) {
        pizzaService.updatePizza(pizza);
        setStandardViewAndAllPizza(modelAndView);
        return modelAndView;
    }
}

package proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proj.domain.Pizza;
import proj.service.interfaces.IPizzaService;
import proj.service.interfaces.IStockService;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;



@Controller
@RequestMapping(value = "/pizza")
public class PizzaController {

    private final IPizzaService pizzaService;
    private final IStockService stockService;
    @Autowired
    public PizzaController(IPizzaService service,IStockService stockService) {
        this.pizzaService = service;
        this.stockService = stockService;
    }

    private void setStandardViewAndAllPizza(ModelAndView modelAndView){
        modelAndView.addObject("allProduct",stockService.getAllProductFromStock());
        modelAndView.addObject("allPizza",pizzaService.getAllPizza());
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
        String[] nums = request.getParameterValues("amount");
        pizzaService.createPizza(pizza,str,nums);

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
    public ModelAndView update( ModelAndView modelAndView,HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        String[] str = request.getParameterValues("prod");
        String[] nums = request.getParameterValues("amount");

        pizzaService.updatePizza(id,str,nums);
        setStandardViewAndAllPizza(modelAndView);
        return modelAndView;
    }
}

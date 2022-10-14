package proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proj.domain.ProductInStock;
import proj.service.interfaces.IStockService;


@Controller
@RequestMapping(value = "stock")
public class ProductInStockController {

    private IStockService stockService;
    @Autowired
    public ProductInStockController(IStockService stockService) {
        this.stockService = stockService;
    }

    private void setStandardViewAndAllObj(ModelAndView modelAndView){
        modelAndView.addObject("allStock",stockService.getAllProductFromStock());
        modelAndView.setViewName("redirect:/stock/");
    }
    @GetMapping(value = "/")
    public ModelAndView stockMain(ModelAndView modelAndView) {
        modelAndView.addObject("allStock",stockService.getAllProductFromStock());
        modelAndView.setViewName("stock.jsp");
        return modelAndView;
    }
    @PostMapping(value = "/create")
    public ModelAndView createProductInStock(ModelAndView modelAndView, @ModelAttribute("product")ProductInStock product) {
        stockService.createProductInStock(product);
        setStandardViewAndAllObj(modelAndView);
        return modelAndView;
    }
    @PostMapping(value = "/update")
    public ModelAndView updateProductInStock(@ModelAttribute("product") ProductInStock product,
                                             ModelAndView modelAndView) {
        System.out.println(product);
        stockService.updateProductInStock(product);
        setStandardViewAndAllObj(modelAndView);
        return modelAndView;
    }
    @GetMapping(value = "/remove/{id}")
    public ModelAndView deleteProduct(ModelAndView modelAndView, @PathVariable long id) {
        stockService.deleteProductFromStockById(id);
        modelAndView.addObject("allStock",stockService.getAllProductFromStock());
        modelAndView.setViewName("redirect:/stock/");
        return modelAndView;
    }
}

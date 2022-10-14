package proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proj.domain.DTO.ProductPojo;
import proj.domain.Product;
import proj.service.interfaces.IProductService;

import java.util.Set;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    private final IProductService productService;


    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
    }

    private void setStandardViewAndAllProduct(ModelAndView modelAndView){
        Set<ProductPojo> set = productService.getAllProduct();
        modelAndView.addObject("allProduct",set);
        modelAndView.setViewName("product.jsp");
    }
    @GetMapping(value = "/")
    public ModelAndView productMain(ModelAndView modelAndView){
        setStandardViewAndAllProduct(modelAndView);
        return modelAndView;
    }

    @PostMapping(value = "/create")
    public ModelAndView createProduct(ModelAndView modelAndView, @ModelAttribute("product") Product product) {
        productService.createProduct(product);
        setStandardViewAndAllProduct(modelAndView);
        return modelAndView;
    }

    @PostMapping(value = "/update")
    public ModelAndView updateProduct(ModelAndView modelAndView,@ModelAttribute("product") Product product) {
        productService.updateProduct(product);
        setStandardViewAndAllProduct(modelAndView);
        return modelAndView;
    }

    //hidden because this functionality can be malicious.
    // deletion of products occurs automatically with the deletion of the pizza

//    @GetMapping(value = "/remove/{id}")
//    public ModelAndView delete(@PathVariable long id, ModelAndView modelAndView) {
//        productService.deleteProduct(id);
//        modelAndView.addObject("allProduct", productService.getAllProduct());
//        modelAndView.setViewName("redirect:/product/");
//        return modelAndView;
//    }
}

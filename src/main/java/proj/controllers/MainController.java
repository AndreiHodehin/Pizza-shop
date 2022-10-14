package proj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping(value = "/login")
    public ModelAndView signIn(ModelAndView modelAndView, HttpServletRequest request) {
        String str = request.getParameter("error");
        if(str != null) {
            modelAndView.addObject("error", "Username or password are wrong");
        }
        modelAndView.setViewName("login.jsp");
        return modelAndView;
    }
}

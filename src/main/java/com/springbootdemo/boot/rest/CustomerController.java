package com.springbootdemo.boot.rest;

import com.springbootdemo.boot.dto.UserDTO;
import com.springbootdemo.boot.entity.User;
import com.springbootdemo.boot.service.CustomerService;
import com.springbootdemo.boot.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    String userName = "userName";
    String products = "products";
    @GetMapping("/")
    public String customerHome(Model model){
        UserDTO loggedInUser =customerService.getUserDetails();
        model.addAttribute(userName,loggedInUser.getFirstName());
        return "home-customer";
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model){
        List<Products> product = customerService.findAllProducts();
        model.addAttribute(products,product);

        return "customer-all-products";
    }

    @GetMapping("/myProducts")
    public String myProducts(Model model){
        UserDTO user = customerService.getUserDetails();
        List<Products> product = user.getProducts();
        model.addAttribute(products,product);
        model.addAttribute(userName,user.getFirstName());
        return "customer-all-products";
    }

    @GetMapping("/addProductForm")
    public String addProductForm(Model model){
        List<Products> product = customerService.findAllProducts();
        model.addAttribute(products,product);
        UserDTO loggedInUser =customerService.getUserDetails();
        model.addAttribute(userName,loggedInUser.getFirstName());
        return "customer-add-product";
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestParam("pId")int id){
        UserDTO user = customerService.getUserDetails();

        customerService.addProduct(user,id);
        return "redirect:/customer/addProductForm";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("productId")int pid){
        UserDTO user = customerService.getUserDetails();
        customerService.deleteProduct(user,pid);
        return "redirect:/customer/myProducts";
    }

    @GetMapping("/username")
    @ResponseBody
    public String getUserDetails(){
        return customerService.getUserDetails().getFirstName();
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }
        return "error";
    }
}

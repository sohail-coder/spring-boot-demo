package com.springbootdemo.boot.rest;

import com.springbootdemo.boot.dao.CustomerDTO;
import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;
import com.springbootdemo.boot.userDetails.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerDTO customerDTO;


//    @Autowired
//    private IAuthenticationFacade authenticationFacade;
//
//    @RequestMapping(value = "/username", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserNameSimple() {
//        Authentication authentication = authenticationFacade.getAuthentication();
//        return authentication.getName();
//    }

    @GetMapping("/")
    public String customerHome(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "home-customer";
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model){
        List<Products> products = customerDTO.findAllProducts();
        model.addAttribute("products",products);
        return "customer-all-products";
    }

    @GetMapping("/myProducts")
    public String myProducts(Model model){
        User user = customerDTO.getUserDetails();
        List<Products> products = user.getProducts();
        model.addAttribute("products",products);
        return "customer-all-products";
    }

    @GetMapping("/addProductForm")
    public String addProductForm(Model model){
        List<Products> products = customerDTO.findAllProducts();
        model.addAttribute("products",products);
        return "customer-add-product";
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestParam("pId")int id){
        User user = customerDTO.getUserDetails();
        customerDTO.addProduct(user,id);
        return "redirect:/customer/addProductForm";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("productId")int pid){
        User user = customerDTO.getUserDetails();
        customerDTO.deleteProduct(user,pid);
        return "redirect:/customer/myProducts";
    }

    @GetMapping("/username")
    @ResponseBody
    public User getUserDetails(){
        return customerDTO.getUserDetails();
    }


}

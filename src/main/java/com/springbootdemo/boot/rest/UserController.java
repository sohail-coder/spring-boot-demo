package com.springbootdemo.boot.rest;

import com.springbootdemo.boot.dto.ProductsDTO;
import com.springbootdemo.boot.dto.UserDTO;
import com.springbootdemo.boot.service.CustomerService;
import com.springbootdemo.boot.service.UserService;
import com.springbootdemo.boot.entity.Authority;
import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    String userName = "userName";
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String userHome(Model model){
        UserDTO loggedInUser =customerService.getUserDetails();
        model.addAttribute(userName,loggedInUser.getFirstName());
        return "home-user";
    }

    @GetMapping("/allUsers")
    public  String allUsers(Model model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "all-users";
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model){
        List<ProductsDTO> products = userService.findAllProducts();
        model.addAttribute("products",products);
        UserDTO loggedInUser =customerService.getUserDetails();
        model.addAttribute(userName,loggedInUser.getFirstName());
        return "all-products";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        Products products =new Products();
        model.addAttribute("product", products);
        UserDTO loggedInUser =customerService.getUserDetails();
        model.addAttribute(userName,loggedInUser.getFirstName());

        return "add-product-form";
    }
    @PostMapping("/addProduct1")
    public String addProductDb(@ModelAttribute("product") Products products){
        userService.addProduct(products);
        return "redirect:/user/allProducts";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("productId")int id, Model model){
        userService.deleteProduct(id);
        return "redirect:/user/allProducts";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("productId")int productId,Model model){
        ProductsDTO products = userService.findProduct(productId);
        model.addAttribute("product", products);
        UserDTO loggedInUser =customerService.getUserDetails();
        model.addAttribute(userName,loggedInUser.getFirstName());
        return "add-product-form";
    }

    @GetMapping("/showFormForAddUser")
    public String showFormForAddUser(Model model){
        User user = new User();
        Authority authority = new Authority();
        model.addAttribute("user",user);
        model.addAttribute("authority",authority);
        return "add-user-form";
    }

    @GetMapping("/showFormForUpdateUser")
    public String showFormForUpdateUser(@RequestParam("userId")String userId,Model model){
        User user = userService.findUser(userId);
        model.addAttribute("user",user);
        return "add-user-form";
    }
    @GetMapping("/users")
    public String users(@RequestParam("productId") int pId,Model model){

        List<User> users = new ArrayList<>();
        users= userService.user(pId);
        model.addAttribute("users",users);
        UserDTO loggedInUser =customerService.getUserDetails();
        model.addAttribute(userName,loggedInUser.getFirstName());
        return "all-users";

    }
    @GetMapping("/addUser")
    public String addUser(Model model){
        return "add-user-form";

    }

}

package com.springbootdemo.boot.rest;

import com.springbootdemo.boot.dao.UserDTO;
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
    private UserDTO userDTO;

    @GetMapping("/")
    public String userHome(){
        return "home-user";
    }

    @GetMapping("/allUsers")
    public  String allUsers(Model model){
        List<User> users = userDTO.findAllUsers();
        model.addAttribute("users",users);
        return "all-users";
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model){
        List<Products> products = userDTO.findAllProducts();
        model.addAttribute("products",products);
        return "all-products";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        Products products =new Products();
        model.addAttribute("product", products);
        return "add-product-form";
    }
    @PostMapping("/addProduct1")
    public String addProductDb(@ModelAttribute("product") Products products){
        userDTO.addProduct(products);
        return "redirect:/user/allProducts";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("productId")int id, Model model){
        userDTO.deleteProduct(id);
        return "redirect:/user/allProducts";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("productId")int productId,Model model){
        Products products = userDTO.findProduct(productId);
        model.addAttribute("product", products);
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
        User user = userDTO.findUser(userId);
        model.addAttribute("user",user);
        return "add-user-form";
    }
    @PostMapping("/adduser1")
    public String addUserDb(@ModelAttribute("user") User user,
                            @ModelAttribute("authority")Authority authority
    ){
        userDTO.addUser(user);
        userDTO.addAuthority(user,authority.getAuthority());
        return "redirect:/user/allUsers";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") String userId){
        userDTO.deleteUser(userId);
        return "redirect:/user/allUsers";
    }

    @GetMapping("/users")
    public String users(@RequestParam("productId") int pId,Model model){

        List<User> users = new ArrayList<>();
        users=userDTO.user(pId);
        model.addAttribute("users",users);
        return "all-users";

    }

}

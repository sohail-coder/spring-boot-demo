package com.springbootdemo.boot.dao;

import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;

import java.util.List;

public interface UserDTO {

    public List<User> findAllUsers();

    public List<Products> findAllProducts();

    public Products findProduct(int id);

    public String addProduct(Products products);

    public String deleteProduct(int productId);

    public User findUser(String id);

    public  String addUser(User user);

    public void deleteUser(String id);

    void addAuthority(User user, String authority);

    public List<User> user(int  id);
}

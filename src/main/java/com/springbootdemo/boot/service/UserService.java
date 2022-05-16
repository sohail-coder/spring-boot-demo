package com.springbootdemo.boot.service;

import com.springbootdemo.boot.dto.ProductsDTO;
import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUsers();

    public List<ProductsDTO> findAllProducts();

    public ProductsDTO findProduct(int id);

    public Products addProduct(Products products);

    public Products deleteProduct(int productId);

    public User findUser(String id);

    public List<User> user(int  id);
}

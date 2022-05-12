package com.springbootdemo.boot.service;

import com.springbootdemo.boot.dto.UserDTO;
import com.springbootdemo.boot.entity.Products;

import java.util.List;

public interface CustomerService {
    public List<Products> findAllProducts();

    public List<Products> findProducts(int id);

    public Products addProduct(UserDTO user, int id);

    public void deleteProduct(UserDTO user,int pid);

    public UserDTO getUserDetails();
}

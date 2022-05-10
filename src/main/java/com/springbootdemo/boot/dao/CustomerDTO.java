package com.springbootdemo.boot.dao;

import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;

import java.util.List;

public interface CustomerDTO {
    public List<Products> findAllProducts();

    public List<Products> findProducts(int id);

    public void addProduct(User user,int id);

    public void deleteProduct(User user,int pid);

    public User getUserDetails();
}

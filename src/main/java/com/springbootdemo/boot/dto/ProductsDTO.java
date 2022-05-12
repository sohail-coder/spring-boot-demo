package com.springbootdemo.boot.dto;

import com.springbootdemo.boot.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ProductsDTO {
    private int id;
    private String name;
    private String price;
    private List<User> users;
}

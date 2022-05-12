package com.springbootdemo.boot.dto;

import com.springbootdemo.boot.entity.Products;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Products> products;
}

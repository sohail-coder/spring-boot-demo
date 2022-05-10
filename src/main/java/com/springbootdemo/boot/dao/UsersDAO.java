package com.springbootdemo.boot.dao;

import com.springbootdemo.boot.entity.Employee;
import com.springbootdemo.boot.entity.User;

import java.util.List;

public interface UsersDAO {
    public List<User> findAll();
    public List<Employee> find();
}

package com.springbootdemo.boot.service;

import com.springbootdemo.boot.dao.ProductRepository;
import com.springbootdemo.boot.dto.ProductsDTO;
import com.springbootdemo.boot.dto.UserDTO;
import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<User> findAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery("from User", User.class);
        return query.getResultList();

    }

    @Override
    public List<ProductsDTO> findAllProducts() {
        return productRepository.findAll().stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductsDTO findProduct(int id) {
        List<ProductsDTO> product;
        product = productRepository.findById(id).stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
        return product.get(0);
    }

    @Override
    @Transactional
    public Products addProduct(Products products) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(products);
        System.out.println(products);
        return products;
    }

    @Override
    @Transactional
    public Products deleteProduct(int productId) {
        Products product = productRepository.getById(productId);
        productRepository.deleteById(productId);

        return product;
    }

    @Override
    public User findUser(String id) {
        Session session = entityManager.unwrap(Session.class);
        return session.find(User.class, id);

    }

    @Override
    @Transactional
    public List<User> user(int id) {
        Session session = entityManager.unwrap(Session.class);
        Products products = session.get(Products.class, id);
        List<User> users = new ArrayList<>();
        users = products.getUsers();
        return users;
    }

    private ProductsDTO convertEntityToDto(Products products) {
        ProductsDTO productsDTO = new ProductsDTO();
        productsDTO.setId(products.getId());
        productsDTO.setName(products.getName());
        productsDTO.setPrice(products.getPrice());
        productsDTO.setUsers(products.getUsers());
        return productsDTO;
    }



//    @Override
//    @Transactional
//    public String addUser(User user) {
//        Session session = entityManager.unwrap(Session.class);
//        user.setPassword("{noop}test");
//        user.setId(1);
////        user.setEnabled(1);
//        session.merge(user);
//
//        return null;
//
//    }

//    @Override
//    @Transactional
//    public void deleteUser(String id) {
//        Session session = entityManager.unwrap(Session.class);
//        Query query =session.createQuery("delete from User u where u.email=:uid");
//        query.setParameter("uid",id);
//        query.executeUpdate();
//    }

//    @Override
//    @Transactional
//    public void addAuthority(User user, String authority) {
//        Session session = entityManager.unwrap(Session.class);
//        user.setId(1);
//        Authority authority1 = new Authority();
//        authority1.setAuthority("ROLE_ADMIN");
////        authority1.setId(1);
////        authority1.setUser(user);
//
//        session.merge(authority1);
//    }


}

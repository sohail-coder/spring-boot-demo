package com.springbootdemo.boot.dao;

import com.springbootdemo.boot.entity.Authority;
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

@Repository
public class UserDTOImpl implements UserDTO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        Query<User> query =session.createQuery("from User",User.class);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public List<Products> findAllProducts() {
        Session session = entityManager.unwrap(Session.class);
        Query<Products> query =session.createQuery("from Products", Products.class);
        List<Products> products = query.getResultList();
        return products;
    }

    @Override
    public Products findProduct(int id) {
        Session session = entityManager.unwrap(Session.class);
        Products products = session.find(Products.class,id);
        return products;
    }

    @Override
    @Transactional
    public String addProduct(Products products) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(products);
        return null;
    }

    @Override
    @Transactional
    public String deleteProduct(int productId) {
        Session session = entityManager.unwrap(Session.class);
        Query query =session.createQuery("delete from Products p where p.id=:pid");
        query.setParameter("pid",productId);
        query.executeUpdate();
        return "redirect:/user/allProducts";
    }

    @Override
    public User findUser(String id) {
        Session session = entityManager.unwrap(Session.class);
        User user= session.find(User.class,id);
        return user;

    }

    @Override
    @Transactional
    public String addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        user.setPassword("{noop}test");
        user.setId(1);
//        user.setEnabled(1);
        session.merge(user);

        return null;

    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        Session session = entityManager.unwrap(Session.class);
        Query query =session.createQuery("delete from User u where u.email=:uid");
        query.setParameter("uid",id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void addAuthority(User user, String authority) {
        Session session = entityManager.unwrap(Session.class);
        user.setId(1);
        Authority authority1 = new Authority();
        authority1.setAuthority("ROLE_ADMIN");
//        authority1.setId(1);
        authority1.setUser(user);

        session.merge(authority1);
    }

    @Override
    @Transactional
    public List<User> user(int id) {
        Session session = entityManager.unwrap(Session.class);
        Products products =session.get(Products.class,id);
        List<User> users = new ArrayList<>();
//        List<String> list=query.list();

//        for(String i: list){
//            users.add(findUser(i));
//        }
        users=products.getUsers();
        return users;
    }
}

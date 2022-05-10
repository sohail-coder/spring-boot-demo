package com.springbootdemo.boot.dao;

import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;
import com.springbootdemo.boot.userDetails.IAuthenticationFacade;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDTOImpl implements CustomerDTO{
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Override
    public List<Products> findAllProducts() {
        Session session = entityManager.unwrap(Session.class);
        Query<Products> query =session.createQuery("from Products", Products.class);
        List<Products> products = query.getResultList();
        return products;
    }

    @Override
    public List<Products> findProducts(int id) {
        Session session = entityManager.unwrap(Session.class);
        User tempUser = session.get(User.class, id);
        List<Products> products=tempUser.getProducts();
        return products;
    }

    @Override
    @Transactional
    public void addProduct(User tempUser,int pid) {
        Session session = entityManager.unwrap(Session.class);
//        User tempUser = session.get(User.class, id);
        Products product = session.get(Products.class,pid);
        List<Products> products=tempUser.getProducts();
        List<User> users=product.getUsers();


        products.add(product);
        users.add(tempUser);

        tempUser.setProducts(products);
        product.setUsers(users);

        session.save(tempUser);
        session.save(product);

    }

    @Override
    @Transactional
    public void deleteProduct(User user,int pid) {
        Session session = entityManager.unwrap(Session.class);
        int i=1;
        List<Products> products = user.getProducts();
        for(Products product : products){
            if(product.getId()!=pid){
//                products.remove(i-1);
                i=i+1;
                continue;
            }
            else{
                break;
            }
        }
        products.remove(i-1);
        user.setProducts(products);
        session.merge(user);
    }

    @Override
    @Transactional
    public User getUserDetails() {
        Authentication authentication = authenticationFacade.getAuthentication();
        String username=authentication.getName();
        Session session = entityManager.unwrap(Session.class);
        int id;
        User tempUser = session.get(User.class, username);
        return tempUser;
    }
}

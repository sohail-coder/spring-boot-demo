package com.springbootdemo.boot.service;

import com.springbootdemo.boot.dao.ProductRepository;
import com.springbootdemo.boot.dao.UserRepository;
import com.springbootdemo.boot.dto.UserDTO;
import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;
import com.springbootdemo.boot.user_details.IAuthenticationFacade;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Override
    public List<Products> findAllProducts() {
        Session session = entityManager.unwrap(Session.class);
        Query<Products> query =session.createQuery("from Products", Products.class);
        return query.getResultList();
    }

    @Override
    public List<Products> findProducts(int id) {
        Session session = entityManager.unwrap(Session.class);
        User tempUser = session.get(User.class, id);
        return tempUser.getProducts();
    }

    @Override
    @Transactional
    public Products addProduct(UserDTO username, int pid) {
        Session session = entityManager.unwrap(Session.class);
        Products product = session.get(Products.class,pid);
        User tempUser = userRepository.getById(username.getEmail());
        List<Products> products=tempUser.getProducts();
        List<User> users=product.getUsers();

        products.add(product);
        users.add(tempUser);

        tempUser.setProducts(products);
        product.setUsers(users);

        session.save(tempUser);
        session.save(product);
        return product;

    }

    @Override
    @Transactional
    public void deleteProduct(UserDTO username,int pid) {
        Session session = entityManager.unwrap(Session.class);
        int i=1;
        User user = userRepository.getById(username.getEmail());
        List<Products> products = user.getProducts();
        for(Products product : products){
            if(product.getId()!=pid){
                i=i+1;
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
    public UserDTO getUserDetails() {
        Authentication authentication = authenticationFacade.getAuthentication();
        String username=authentication.getName();
        List<UserDTO> users = userRepository.findById(username).stream().map(this::convertUertoDto)
                .collect(Collectors.toList());
        return users.get(0);
    }
    private UserDTO convertUertoDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setProducts(user.getProducts());
        userDTO.setEmail(user.getEmail());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        return userDTO;
    }
}

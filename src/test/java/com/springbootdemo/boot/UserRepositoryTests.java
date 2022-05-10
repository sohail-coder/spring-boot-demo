package com.springbootdemo.boot;

import com.springbootdemo.boot.dao.UserDTO;
import com.springbootdemo.boot.entity.Authority;
import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;
import com.springbootdemo.boot.repository.AuthorityRepository;
import com.springbootdemo.boot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDTO userDTO;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void test(){

    }

    @Test
    public void testCreateProduct(){
        Products products = new Products("pine","90");
        testEntityManager.persist(products);
    }
    @Test
    public void testCreateUser(){
        User user = new User("suresh","Lenin","ramesh@gmail.com","123456","test");
        testEntityManager.persist(user);
    }








//    @Test
//    public void testCreateAuth(){
//        Authority  authority = new Authority("ROLE_CUSTOMER",4);
////        User user =userRepository.findById("ramesh@gmail.com").get();
////        user.getAuthorities().add(authority);
//        User users = userRepository.findById("ramesh@gmail.com").get();
//        authority.setUser(users);
//        authorityRepository.saveAndFlush(authority);
//        testEntityManager.merge(authority);
////        testEntityManager.merge(user);
//
//    }
//    @Test
//    public void addAuthority(){
//        User user =userRepository.findById("ramesh@gmail.com").get();
//        Authority authority = new Authority("ROLE_CUSTOMER",4);
//        List<Authority> authorities = new ArrayList<>();
//        authorities.add(authority);
//        user.setAuthorities(authorities);
////        userRepository.
//        authority.setUser(user);
////        testEntityManager.persist(user);
////        testEntityManager.merge(user);
//        authorityRepository.save(authority);
//        testEntityManager.persist(authority);
//        System.out.println(user);
//    }


}

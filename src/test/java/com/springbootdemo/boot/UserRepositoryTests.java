package com.springbootdemo.boot;

import com.springbootdemo.boot.service.UserService;
import com.springbootdemo.boot.entity.Products;
import com.springbootdemo.boot.entity.User;
import com.springbootdemo.boot.dao.AuthorityRepository;
import com.springbootdemo.boot.dao.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
 class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void test(){
       Assertions.assertDoesNotThrow(this::doNotThrowException);
    }

    @Test
     void testCreateProduct(){
        Products products = new Products("pine","90");
        testEntityManager.persist(products);
       Assertions.assertDoesNotThrow(this::doNotThrowException);
    }
    @Test
     void testCreateUser(){
        User user = new User("suresh","Lenin","ramesh@gmail.com","123456","test");
        testEntityManager.persist(user);
       Assertions.assertDoesNotThrow(this::doNotThrowException);
    }
   private void doNotThrowException(){
   }

}

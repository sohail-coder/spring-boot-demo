package com.springbootdemo.boot.dao;

import com.springbootdemo.boot.entity.Employee;
import com.springbootdemo.boot.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class UsersDAOImpl implements UsersDAO{

    private EntityManager entityManager;
    @Autowired
    public UsersDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public List<User> findAll() {
        Session session=entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery("from Users", User.class);

        List<User> employees = query.getResultList();
        return employees;
    }

    @Override
    @Transactional
    public List<Employee> find() {
        Session session=entityManager.unwrap(Session.class);
        Query<Employee> query = session.createQuery("from Employee",Employee.class);

        List<Employee> employees = query.getResultList();
        return employees;
    }

}

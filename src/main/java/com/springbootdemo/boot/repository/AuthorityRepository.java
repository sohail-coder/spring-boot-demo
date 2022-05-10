package com.springbootdemo.boot.repository;

import com.springbootdemo.boot.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
}

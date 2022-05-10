package com.springbootdemo.boot.entity;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "authority")
    private String authority;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "username")
    private User user;

    public Authority() {
    }
    public Authority(String authority, int id) {
        this.id = id;
        this.authority = authority;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


package com.springbootdemo.boot.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade=
                    {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH}
                    ,mappedBy = "products")
//    @JoinTable(
//            name="user_product",
//            joinColumns=@JoinColumn(name="product_id"),
//            inverseJoinColumns=@JoinColumn(name="user_id")
//
//    )
    private List<User> users;

    public Products() {
    }

    public Products(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Products(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

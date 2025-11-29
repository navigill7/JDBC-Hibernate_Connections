package com.data_jpa.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserClassSpringBoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // wrapper Long, so it can be null when transient

    private String name;
    private String email;

    public UserClassSpringBoot() { }

    // Constructor for creating NEW users — do NOT include id
    public UserClassSpringBoot(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getters / setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }
}

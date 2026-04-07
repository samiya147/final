package org.example.demooooooo.Entity;

import jakarta.persistence.*;

@Entity
public class users1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String phone;

    // PROFILE FIELDS
    private String address;
    private String childName;
    private Integer age;

    // ================= GETTERS =================

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getChildName() {
        return childName;
    }

    public Integer getAge() {
        return age;
    }

    // ================= SETTERS =================

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
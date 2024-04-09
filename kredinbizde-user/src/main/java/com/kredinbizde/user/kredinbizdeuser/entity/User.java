package com.kredinbizde.user.kredinbizdeuser.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "users", indexes = @Index(columnList = "email", unique = true))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String address;
}
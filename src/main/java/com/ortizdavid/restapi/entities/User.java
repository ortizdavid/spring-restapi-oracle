package com.ortizdavid.restapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @NotBlank(message = "UserName is mandatory")
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Active status is mandatory")
    @Column(name = "active")
    private int active;
    
}

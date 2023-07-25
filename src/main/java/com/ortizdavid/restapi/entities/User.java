package com.ortizdavid.restapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Username is mandatory")
    @Size(min = 8, message="Username must have a least 8 characteres")
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message="Password must have a least 8 characteres")
    @Column(name = "password")
    private String password;

    @Column(name = "active")
    @Min(value = 0, message = "The 'active' field must be at least 0.")
    @Max(value = 1, message = "The 'active' field must be less or equal than 1.")
    private int active;
    
}

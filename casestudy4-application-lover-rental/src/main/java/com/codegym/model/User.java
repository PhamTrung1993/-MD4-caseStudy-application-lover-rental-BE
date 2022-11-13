package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 6, max = 18, message = "Username must be between 6 and 18 characters")
    private String userName;
    @NotEmpty
    @Size(min = 6, max = 18, message = "Username must be between 6 and 18 characters")
    private String password;
    @NotEmpty
    @Email( message = "Please enter the correct email format")
    private String email;
    @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)", message = "Please enter the correct phone number format")
    private String phone;

    private LocalDate joinDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    private String vip;
    public User(String email, String password, Role role) {
    }
}

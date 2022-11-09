package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "provider")
@Getter
@Setter
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int age;

    private String gender;

    private String name;

    private String city;

    private String nationality;

    private String avatar;

    @OneToMany
    @JoinColumn(name = "provider_id")
    private Set<Image> image;
    private String height;
    private String weight;
    private String hobby;
    private String description;
    private String facebook;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "provider_service", joinColumns = {@JoinColumn(name = "provider_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")})
    private Set<Service> service;
    private int price;
    private String status;
    private long hasBeenHired;
    private long view;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



}

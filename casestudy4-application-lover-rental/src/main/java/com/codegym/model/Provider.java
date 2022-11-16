package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
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


    @NotEmpty
    @Min(value = 18,message = "can't enter more less 18 ages")

    private int age;

    private String gender;

    private String name;

    private String city;

    private String nationality;
    private String height;
    private String weight;
    private String hobby;
    private String description;
    private String facebook;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "provider_services", joinColumns = {@JoinColumn(name = "provider_id")},
            inverseJoinColumns = {@JoinColumn(name = "services_id")})
    private Set<Services> service;
    private int price;

    private String status;
    private long hasBeenHired;
    private long view;

//    @ManyToOne
//    @JoinTable(name = "provider_avatar", joinColumns = {@JoinColumn(name = "provider_id")},
//            inverseJoinColumns = {@JoinColumn(name = "avatar_id")})
//    private Avatar avatar;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


//    @OneToMany
//    @JoinTable(name = "provider_image", joinColumns = {@JoinColumn(name = "provider_id")},
//            inverseJoinColumns = {@JoinColumn(name = "image_id")})
//    private List<Image> images;
}

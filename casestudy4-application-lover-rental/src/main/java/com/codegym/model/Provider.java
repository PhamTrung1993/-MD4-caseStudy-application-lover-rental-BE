package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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

    private String avatar;

    @OneToMany
    @JoinColumn(name = "provider_id")
    private List<Image> image;
    private String height;
    private String weight;
    private String hobby;
    private String description;
    private String facebook;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "provider_service", joinColumns = {@JoinColumn(name = "provider_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")})
    private Set<Services> service;
    private int price;
    private String status;
    private long hasBeenHired;
    private long view;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Provider buildByProvider(ProviderForm providerForm){
        Provider provider = new Provider();
        List<MultipartFile> images = providerForm.getImage();
        for (int i = 0; i < images.size(); i++) {
               Image image1 = new Image(images.get(i).getOriginalFilename());
               provider.getImage().add(image1);
        }
        String avatarName = providerForm.getAvatar().getOriginalFilename();
        provider.setId(providerForm.getId());
        provider.setAge(providerForm.getAge());
        provider.setCity(providerForm.getCity());
        provider.setNationality(providerForm.getNationality());
        provider.setAvatar(avatarName);
        provider.setDescription(providerForm.getDescription());
        provider.setFacebook(providerForm.getFacebook());
        provider.setService(providerForm.getService());
        provider.setWeight(providerForm.getWeight());
        provider.setHeight(providerForm.getHeight());
        provider.setHobby(providerForm.getHobby());
        provider.setGender(providerForm.getGender());
        provider.setPrice(providerForm.getPrice());
        return provider;
    }
}

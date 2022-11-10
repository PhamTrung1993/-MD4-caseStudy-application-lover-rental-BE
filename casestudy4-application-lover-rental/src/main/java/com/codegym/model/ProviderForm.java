package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProviderForm {
    private Long id;
    @NotEmpty
    @Min(value = 18, message = "can't enter more less 18 Age")
    @Pattern(regexp = "^[0-9._]*$", message = "do not special characters")
    private int age;
    @NotEmpty
    private String gender;
    @NotEmpty
    private String name;
    @NotEmpty
    private String city;
    @NotEmpty
    private String nationality;
    @NotEmpty
    @Pattern(regexp = ".*.jpg|img", message = "Incorrect file format")
    private MultipartFile avatar;

    private List<MultipartFile> image ;
    private String height;
    private String weight;
    private String hobby;
    private String description;
    private String facebook;
    private Set<Services> service;
    private int price;
    private String status;
    private long hasBeenHired;
    private long view;
    private User user;
}

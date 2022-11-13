package com.codegym.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProviderDTO {
    private Long id;
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
    private List<Long> servicesId;
    private int price;
    private String status;
    private long hasBeenHired;
    private long view;
    private Long userId;
}

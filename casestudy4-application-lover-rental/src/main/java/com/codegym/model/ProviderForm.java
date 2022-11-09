package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProviderForm {
    private Long id;
    @NotEmpty
    @Min(value = 18, message = "can't enter more less 18 Age")
    private int age;
    private String gender;
    private String name;
    private String city;
    private String nationality;
    private MultipartFile avatar;
    private Set<Image> image ;
    private String height;
    private String weight;
    private String hobby;
    private String description;
    private String facebook;

}

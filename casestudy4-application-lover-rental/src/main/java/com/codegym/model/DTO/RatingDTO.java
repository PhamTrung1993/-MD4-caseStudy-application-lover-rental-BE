package com.codegym.model.DTO;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private Long id;
    private String comment;
    private Long providerId;
}

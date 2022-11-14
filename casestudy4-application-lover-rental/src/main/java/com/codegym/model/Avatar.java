package com.codegym.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "avatars")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

}

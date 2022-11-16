package com.codegym;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate doB;
    private String address;
    private String phoneNumber;
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private Classroom classRoom;
}

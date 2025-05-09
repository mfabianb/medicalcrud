package com.example.meds.model.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.persistence.Column;


import java.util.List;

@Entity
@Table(name="DOCTOR")
@Data
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @Column(name="ID_DOCTOR")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idDoctor;

    @Column(name="NAME")
    private String name;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="SPECIALIZATION")
    private String specialization;

}

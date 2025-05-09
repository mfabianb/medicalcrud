package com.example.meds.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="PATIENT")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @javax.persistence.Column(name="ID_PATIENT")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idPatient;

    @javax.persistence.Column(name="NAME")
    private String name;

    @javax.persistence.Column(name="LAST_NAME")
    private String lastName;

    @javax.persistence.Column(name="EMAIL")
    private String email;

}

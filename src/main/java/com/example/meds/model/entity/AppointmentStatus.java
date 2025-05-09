package com.example.meds.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="APPOINTMENT_STATUS")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentStatus {

    @Id
    @javax.persistence.Column(name="ID_APPOINTMENT_STATUS")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idAppointmentStatus;

    @javax.persistence.Column(name="STATUS")
    private String status;

}

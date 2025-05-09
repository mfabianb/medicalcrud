package com.example.meds.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="APPOINTMENT")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @javax.persistence.Column(name="ID_APPOINTMENT")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idAppointment;

    @javax.persistence.Column(name="APPOINTMENT_DATE_TIME")
    private LocalDateTime appointmentDateTime;

    @ManyToOne
    @JoinColumn(name = "ID_DOCTOR")
    private Doctor idDoctor;

    @ManyToOne
    @JoinColumn(name = "ID_PATIENT")
    private Patient idPatient;

    @ManyToOne
    @JoinColumn(name = "ID_APPOINTMENT_STATUS")
    private AppointmentStatus idAppointmentStatus;

    @ManyToOne
    @JoinColumn(name = "ID_CONSULTING_ROOM")
    private ConsultingRoom idConsultingRoom;


}

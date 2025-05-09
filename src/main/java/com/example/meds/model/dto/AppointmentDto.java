package com.example.meds.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long idDoctor;
    private Long idPatient;
    private Long idConsultingRoom;
    private String appointmentDateTime;
}

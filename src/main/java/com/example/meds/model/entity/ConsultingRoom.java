package com.example.meds.model.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.persistence.Column;

@Entity
@Table(name="CONSULTING_ROOM")
@Data
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultingRoom {
    @Id
    @Column(name="ID_CONSULTING_ROOM")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idConsultingRoom;

    @Column(name="ROOM")
    private int room;

    @Column(name="FLOOR")
    private int floor;
}

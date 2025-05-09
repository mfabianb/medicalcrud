package com.example.meds.repository;

import com.example.meds.model.entity.Appointment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE " +
            "(:idDoctor IS NULL OR a.idDoctor.idDoctor = :idDoctor) " +
            "AND (:idConsultingRoom IS NULL OR a.idConsultingRoom.idConsultingRoom = :idConsultingRoom) " +
            "AND (:dateTime IS NULL OR a.appointmentDateTime = :dateTime) ")
    List<Appointment> findPageable(@Param("idDoctor") Long idDoctor, @Param("idConsultingRoom") Long idConsultingRoom, @Param("dateTime") LocalDateTime dateTime,
                                                                  Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.idDoctor.idDoctor = :idDoctor")
    List<Appointment> findByIdDoctor(@Param("idDoctor") Long idDoctor);

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.idDoctor.idDoctor = :idDoctor")
    Integer countAppointmentsByIdDoctor(@Param("idDoctor") Long idDoctor);
}

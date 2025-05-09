package com.example.meds.repository;

import com.example.meds.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository <Doctor, Long>{
    public Optional<Doctor> findById(Long idDoctor);
}

package com.example.meds.repository;

import com.example.meds.model.entity.Doctor;
import com.example.meds.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}

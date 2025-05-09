package com.example.meds.service;

import com.example.meds.exception.MedicalBusinessException;
import com.example.meds.model.entity.Doctor;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor) throws MedicalBusinessException;

    Doctor getDoctor(Long idDoctor) throws MedicalBusinessException;

    Doctor updateDoctor(Doctor doctor) throws MedicalBusinessException;
}

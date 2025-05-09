package com.example.meds.service.impl;

import com.example.meds.constants.MedicalConstants;
import com.example.meds.exception.MedicalBusinessException;
import com.example.meds.model.entity.Doctor;
import com.example.meds.repository.DoctorRepository;
import com.example.meds.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor saveDoctor(Doctor doctor) throws MedicalBusinessException {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getIdDoctor());

        if(!doctorOptional.isEmpty()){
            throw new MedicalBusinessException(MedicalConstants.DOCTOR_ALREADY_EXISTS);
        }

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctor(Long idDoctor) throws MedicalBusinessException {
        return doctorRepository.findById(idDoctor).orElseThrow(() -> new MedicalBusinessException(MedicalConstants.DOCTOR_NOT_FOUND));
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) throws MedicalBusinessException {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getIdDoctor());

        if(doctorOptional.isEmpty()){
            throw new MedicalBusinessException(MedicalConstants.DOCTOR_ALREADY_EXISTS);
        }

        return doctorRepository.save(doctor);
    }

}

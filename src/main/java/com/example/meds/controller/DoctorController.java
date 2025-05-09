package com.example.meds.controller;

import com.example.meds.constants.MedicalConstants;
import com.example.meds.exception.MedicalBusinessException;
import com.example.meds.model.dto.ResponseErrorDto;
import com.example.meds.model.entity.Doctor;
import com.example.meds.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private static final Logger log = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Object> createDoctor(@RequestBody Doctor doctor) {
        try {
            return new ResponseEntity<>(doctorService.saveDoctor(doctor), HttpStatus.OK);
        } catch (MedicalBusinessException e) {
            log.error(MedicalConstants.DOCTOR_ALREADY_EXISTS, doctor.getIdDoctor());
            log.error(e.toString());
            return new ResponseEntity<>(new ResponseErrorDto(String.format(MedicalConstants.DOCTOR_ALREADY_EXISTS_F, doctor.getIdDoctor()),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDoctor(@PathVariable Long idDoctor) {
        try {
            return new ResponseEntity<>(doctorService.getDoctor(idDoctor), HttpStatus.OK);
        } catch (MedicalBusinessException e) {
            log.error(MedicalConstants.DOCTOR_NOT_FOUND, idDoctor);
            log.error(e.toString());
            return new ResponseEntity<>(new ResponseErrorDto(String.format(MedicalConstants.DOCTOR_NOT_FOUND_F, idDoctor),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateDoctor(@RequestBody Doctor doctor) {
        try {
            return new ResponseEntity<>(doctorService.updateDoctor(doctor), HttpStatus.OK);
        } catch (MedicalBusinessException e) {
            log.error(MedicalConstants.DOCTOR_NOT_FOUND, doctor.getIdDoctor());
            log.error(e.toString());
            return new ResponseEntity<>(new ResponseErrorDto(String.format(MedicalConstants.DOCTOR_NOT_FOUND_F, doctor.getIdDoctor()),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

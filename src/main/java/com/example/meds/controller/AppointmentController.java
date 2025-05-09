package com.example.meds.controller;

import com.example.meds.constants.MedicalConstants;
import com.example.meds.exception.MedicalBusinessException;
import com.example.meds.model.dto.AppointmentDto;
import com.example.meds.model.dto.ResponseErrorDto;
import com.example.meds.model.entity.Doctor;
import com.example.meds.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private static final Logger log = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Object> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        log.info("appointmentDto: {}", appointmentDto.toString());
        try {
            return new ResponseEntity<>(appointmentService.createAppointment(appointmentDto), HttpStatus.OK);
        } catch (MedicalBusinessException e) {
            log.info("{}: {}", e.getMessage(), e);
            return new ResponseEntity<>(new ResponseErrorDto(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAppointment(@RequestBody AppointmentDto appointmentDto) {
        try {
            return new ResponseEntity<>(appointmentService.getAppointment(appointmentDto), HttpStatus.OK);
        } catch (MedicalBusinessException e) {
            log.error(MedicalConstants.DOCTOR_ALREADY_EXISTS, appointmentDto.getIdDoctor());
            log.info("{}: {}", e.getMessage(), e);
            return new ResponseEntity<>(new ResponseErrorDto(e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

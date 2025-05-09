package com.example.meds.service;

import com.example.meds.exception.MedicalBusinessException;
import com.example.meds.model.dto.AppointmentDto;
import com.example.meds.model.entity.Appointment;

import java.text.ParseException;
import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(AppointmentDto appointmentDto) throws MedicalBusinessException;

    List<Appointment> getAppointment(AppointmentDto appointmentDto) throws MedicalBusinessException;
}

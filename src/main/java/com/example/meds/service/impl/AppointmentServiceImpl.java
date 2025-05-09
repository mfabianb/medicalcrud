package com.example.meds.service.impl;

import com.example.meds.constants.MedicalConstants;
import com.example.meds.controller.AppointmentController;
import com.example.meds.exception.MedicalBusinessException;
import com.example.meds.model.dto.AppointmentDto;
import com.example.meds.model.entity.*;
import com.example.meds.repository.AppointmentRepository;
import com.example.meds.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(AppointmentDto appointmentDto) throws MedicalBusinessException {

        int countDoctorAppointments = appointmentRepository.countAppointmentsByIdDoctor(appointmentDto.getIdDoctor());

        if(countDoctorAppointments > 8){
            throw new MedicalBusinessException(MedicalConstants.ONLY_8_APPOINTMENTS_PER_DOCTOR);
        }

        List<Appointment> appointmentList = appointmentRepository.findByIdDoctor(appointmentDto.getIdDoctor());

        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setIdAppointmentStatus(1L);

        ConsultingRoom consultingRoom = new ConsultingRoom();
        consultingRoom.setIdConsultingRoom(appointmentDto.getIdConsultingRoom());

        Patient patient = new Patient();
        patient.setIdPatient(appointmentDto.getIdPatient());

        Doctor doctor = new Doctor();
        doctor.setIdDoctor(appointmentDto.getIdDoctor());

        Appointment newAppointment = Appointment.builder()
                .idAppointmentStatus(appointmentStatus)
                .idConsultingRoom(consultingRoom)
                .idPatient(patient)
                .idDoctor(doctor)
                .appointmentDateTime(convertDtoToDateTime(appointmentDto))
                .build();

        for(Appointment appointment: appointmentList){
            validateSameConsultingRoomAndDateTime(appointment, newAppointment);
            validateDateTime(appointment, newAppointment);
            validateSameDoctorAndDateTime(appointment, newAppointment);
        }

        return appointmentRepository.save(Appointment.builder()
                .idAppointmentStatus(appointmentStatus)
                .idConsultingRoom(consultingRoom)
                .idPatient(patient)
                .idDoctor(doctor)
                .appointmentDateTime(convertDtoToDateTime(appointmentDto))
                .build());
    }

    @Override
    public List<Appointment> getAppointment(AppointmentDto appointmentDto) throws MedicalBusinessException {
        Pageable sortedByName = PageRequest.of(0, 3, Sort.by("idDoctor"));
        return appointmentRepository.findPageable(appointmentDto.getIdDoctor(), appointmentDto.getIdConsultingRoom(), convertDtoToDateTime(appointmentDto), sortedByName);
    }

    private LocalDateTime convertDtoToDateTime(AppointmentDto appointmentDto) throws MedicalBusinessException {
        try{

            if(Objects.isNull(appointmentDto.getAppointmentDateTime()) || appointmentDto.getAppointmentDateTime().isEmpty()){
                return null;
            }

            LocalDateTime localDateTime = LocalDateTime.parse(appointmentDto.getAppointmentDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            return localDateTime;
        } catch (Exception e) {
            throw new MedicalBusinessException(MedicalConstants.APPOINTMENT_DATA_CANNOT_BE_NULL_OR_EMPTY);
        }
    }

    private void validateDateTime(Appointment currentAppointment, Appointment newAppointment) throws MedicalBusinessException {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newStart = newAppointment.getAppointmentDateTime();

        if (newStart.isBefore(now)) {
            throw new MedicalBusinessException(MedicalConstants.APPOINTMENT_DATE_TIME_ERROR_PAST);
        }

        if (Duration.between(now, newStart).toMinutes() < 120) {
            throw new MedicalBusinessException(MedicalConstants.APPOINTMENT_DATE_TIME_ERROR_PLUS_HOURS);
        }

        LocalDateTime existingStart = currentAppointment.getAppointmentDateTime();

        if (existingStart.toLocalDate().equals(newStart.toLocalDate())) {
            long minutesDiff = Math.abs(Duration.between(existingStart, newStart).toMinutes());

            if (minutesDiff < 120) {
                throw new MedicalBusinessException(MedicalConstants.APPOINTMENT_DATE_TIME_ERROR_SAME_DAY_TOO_CLOSE);
            }

            if (existingStart.equals(newStart)) {
                throw new MedicalBusinessException(MedicalConstants.APPOINTMENT_DATE_TIME_ERROR_DUPLICATE);
            }
        }

    }

    private void validateSameConsultingRoomAndDateTime(Appointment currentAppointment, Appointment newAppointment) throws MedicalBusinessException {
        if(currentAppointment.getIdConsultingRoom().getIdConsultingRoom().equals(newAppointment.getIdConsultingRoom().getIdConsultingRoom())
                && currentAppointment.getAppointmentDateTime().isEqual(newAppointment.getAppointmentDateTime())){
            throw new MedicalBusinessException(MedicalConstants.SAME_ROOM_AND_DATETIME);
        }
    }

    private void validateSameDoctorAndDateTime(Appointment currentAppointment, Appointment newAppointment) throws MedicalBusinessException {
        log.info("curr: {}, new: {}", currentAppointment.getAppointmentDateTime(), newAppointment.getAppointmentDateTime());
        if(currentAppointment.getIdDoctor().getIdDoctor().equals(newAppointment.getIdDoctor().getIdDoctor())
                && currentAppointment.getAppointmentDateTime().isEqual(newAppointment.getAppointmentDateTime())){
            throw new MedicalBusinessException(MedicalConstants.SAME_DATETIME_AND_DOCTOR);
        }
    }

}

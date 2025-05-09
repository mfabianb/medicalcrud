package com.example.meds.constants;

public class MedicalConstants {
    public static final String DOCTOR_NOT_FOUND = "Doctor with id {} not found";
    public static final String DOCTOR_ALREADY_EXISTS = "Doctor with id {} is already registered";
    public static final String DOCTOR_NOT_FOUND_F = "Doctor with id %s not found";
    public static final String DOCTOR_ALREADY_EXISTS_F = "Doctor with id %s is already registered";
    public static final String APPOINTMENT_DATA_CANNOT_BE_NULL_OR_EMPTY = "Some appointment input data is null or empty, it can not be";
    public static final String APPOINTMENT_DATE_TIME_ERROR_PAST = "Appointment can not be scheduled in a past date time from today, please check input data";
    public static final String APPOINTMENT_DATE_TIME_ERROR_PLUS_HOURS = "Appointment must be scheduled at least within two hours, please check input data";
    public static final String SAME_ROOM_AND_DATETIME= "Appointment can not be scheduled in the same consulting room and date time that other previous appointment, please check input data";
    public static final String SAME_ROOM_AND_DOCTOR = "A doctor can not be in two places at same time, please check input data";
    public static final String APPOINTMENT_DATE_TIME_ERROR_SAME_DAY_TOO_CLOSE = "Appointment can not be scheduled for the same date time that other previous appointment, please check input data";
    public static final String APPOINTMENT_DATE_TIME_ERROR_DUPLICATE = "Appointment is the same than other previous appointment, please check input data";
    public static final String SAME_DATETIME_AND_DOCTOR = "Appointment can not be scheduled for the same doctor and date time that other previous appointment, please check input data";
    public static final String ONLY_8_APPOINTMENTS_PER_DOCTOR = "One doctor only can attend 8 appointments per day";
}

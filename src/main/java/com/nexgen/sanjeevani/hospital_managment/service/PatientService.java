package com.nexgen.sanjeevani.hospital_managment.service;

import com.nexgen.sanjeevani.hospital_managment.dto.AppointmentRequestDto;
import com.nexgen.sanjeevani.hospital_managment.dto.AppointmentResponseDto;
import com.nexgen.sanjeevani.hospital_managment.dto.PatientDto;
import com.nexgen.sanjeevani.hospital_managment.dto.PatientResponseDto;
import com.nexgen.sanjeevani.hospital_managment.model.Appointment;

import java.util.List;

/*
--> Scope of this class <--
 1. Register { Give : Username and password}
 2. Login
 3. Update details
 4. Book Appointment with doctor
 5. See/update/cancel Appointment details/status
 */
public interface PatientService {
    //Register
    String registerPatient(PatientDto patient);

    //Login
    PatientResponseDto loginPatient(String userName, String password);

    //Update
    PatientResponseDto updatePatient(PatientDto patient);

    //Book Appointment
    AppointmentResponseDto addAppointment(AppointmentRequestDto appointment, Long patientId);

    //Get All Appointment
    List<AppointmentResponseDto> getAllAppointments(Long patientId);

    //Get Appointment
    AppointmentResponseDto getAppointment(Long appointmentId);

    //Delete Appointment
    void deleteAppointment(Long appointmentId);

    //Update Appointment
    AppointmentResponseDto updateAppointment(AppointmentRequestDto appointment ,Long patientId);
}

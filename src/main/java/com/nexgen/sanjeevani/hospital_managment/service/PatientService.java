package com.nexgen.sanjeevani.hospital_managment.service;

import com.nexgen.sanjeevani.hospital_managment.dto.PatientDto;
import com.nexgen.sanjeevani.hospital_managment.model.Appointment;
import com.nexgen.sanjeevani.hospital_managment.model.Patient;

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
    Patient loginPatient(String userName, String password);

    //Update
    PatientDto updatePatient(PatientDto patient);

    //Book Appointment
    Appointment addAppointment(Appointment appointment,Long patientId);

    //Get All Appointment
    List<Appointment> getAllAppointments(Long patientId);

    //Get Appointment
    Appointment getAppointment(String appointmentId);

    //Delete Appointment
    void deleteAppointment(String appointmentId);

    //Update Appointment
    Appointment updateAppointment(Appointment appointment ,Long patientId);
}

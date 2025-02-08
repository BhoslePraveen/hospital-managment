package com.nexgen.sanjeevani.hospital_managment.controller;

import com.nexgen.sanjeevani.hospital_managment.dto.AppointmentRequestDto;
import com.nexgen.sanjeevani.hospital_managment.dto.AppointmentResponseDto;
import com.nexgen.sanjeevani.hospital_managment.dto.PatientDto;
import com.nexgen.sanjeevani.hospital_managment.dto.PatientResponseDto;
import com.nexgen.sanjeevani.hospital_managment.model.Appointment;
import com.nexgen.sanjeevani.hospital_managment.model.Patient;
import com.nexgen.sanjeevani.hospital_managment.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
--> Scope of this class <--
 1. Register { Give : Username and password}
 2. Login
 3. Update details
 4. Book Appointment with doctor
 5. See/update/cancel Appointment details/status
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    //HTTP Request : POST(Add a resource)
    @PostMapping("/register")
    public ResponseEntity<String> registerPatient(@RequestBody @Valid PatientDto patient){
        return ResponseEntity.ok(patientService.registerPatient(patient));
    }

    @GetMapping("/login")
    public ResponseEntity<PatientResponseDto> loginPatient(@RequestParam String userName, @RequestParam String password) {
        return ResponseEntity.ok(patientService.loginPatient(userName, password));
    }

    @PutMapping("/update")
    public ResponseEntity<PatientResponseDto> updatePatient(@RequestBody @Valid PatientDto patient){
        return ResponseEntity.ok(patientService.updatePatient(patient));
    }

    @PostMapping("/book")
    public ResponseEntity<AppointmentResponseDto> addAppointment(@RequestBody @Valid AppointmentRequestDto appointment, @RequestParam Long patientId){
        return ResponseEntity.ok(patientService.addAppointment(appointment, patientId));
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments(@PathVariable (name = "id") Long patientId){
        return ResponseEntity.ok(patientService.getAllAppointments(patientId));
    }

    @GetMapping("/appointments/{id}/details")
    public ResponseEntity<AppointmentResponseDto> getAppointment(@PathVariable (name = "id") Long appointmentId){
        return ResponseEntity.ok(patientService.getAppointment(appointmentId));
    }

    @DeleteMapping("/appointments/{id}")
    public void deleteAppointment(@PathVariable (name = "id") Long appointmentId){
        patientService.deleteAppointment(appointmentId);
    }

}

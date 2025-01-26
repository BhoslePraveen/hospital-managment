package com.nexgen.sanjeevani.hospital_managment.controller;

import com.nexgen.sanjeevani.hospital_managment.dto.PatientDto;
import com.nexgen.sanjeevani.hospital_managment.model.Patient;
import com.nexgen.sanjeevani.hospital_managment.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Patient> loginPatient(@RequestParam String userName, @RequestParam String password){
        return ResponseEntity.ok(patientService.loginPatient(userName,password));
    }

}

package com.nexgen.sanjeevani.hospital_managment.service;
import java.time.LocalDateTime;

import com.nexgen.sanjeevani.hospital_managment.dto.PatientDto;
import com.nexgen.sanjeevani.hospital_managment.model.Appointment;
import com.nexgen.sanjeevani.hospital_managment.model.Patient;
import com.nexgen.sanjeevani.hospital_managment.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public String registerPatient(PatientDto patient) {
        log.info("START: Registering the patient");
        //Step 1: Convert the dto to Entity.
        Patient patientEntity = new Patient();
        patientEntity.setUserName(patient.getUserName());
        patientEntity.setPassword(patient.getPassword());
        patientEntity.setFirstName(patient.getFirstName());
        patientEntity.setLastName(patient.getLastName());
        patientEntity.setEmail(patient.getEmail());
        patientEntity.setPhone(patient.getPhone());
        patientEntity.setGender(patient.getGender().name());

        //Step 2 : store the entity in db
        try {
            patientRepository.save(patientEntity);
            log.info("END: Registering the patient");
            return "Patient Registered Successfully";
        } catch (Exception e) {
            e.printStackTrace(); //Do not do this in prod
            log.error("Exception Occurred while Registering the patient : {}",e.getMessage()); // Do this in prod
            return "Something went wrong. Please try again later.";
        }
    }

    @Override
    public Patient loginPatient(String userName, String typedPassword) {
        Optional<Patient> patient = patientRepository.findByUserName(userName);
        if(patient.isPresent()){
            if(patient.get().getPassword().equals(typedPassword)){
                return patient.get();
            } else {
                throw new RuntimeException("Invalid Password");
            }
        }else {
            throw new RuntimeException("Invalid UserName");
        }
    }

    @Override
    public PatientDto updatePatient(PatientDto patient) {
        //TODO: Ashwini Implementation for update api
        return null;
    }


    @Override
    public Appointment addAppointment(Appointment appointment, Long patientId) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments(Long patientId) {
        return List.of();
    }

    @Override
    public Appointment getAppointment(String appointmentId) {
        return null;
    }

    @Override
    public void deleteAppointment(String appointmentId) {

    }

    @Override
    public Appointment updateAppointment(Appointment appointment, Long patientId) {
        return null;
    }
}

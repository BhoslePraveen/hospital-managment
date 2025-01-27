package com.nexgen.sanjeevani.hospital_managment.service;
import com.nexgen.sanjeevani.hospital_managment.dto.SymtomDto;
import com.nexgen.sanjeevani.hospital_managment.dto.*;
import com.nexgen.sanjeevani.hospital_managment.model.Symtom;
import com.nexgen.sanjeevani.hospital_managment.model.Doctor;
import java.util.ArrayList;

import com.nexgen.sanjeevani.hospital_managment.enums.Gender;

import com.nexgen.sanjeevani.hospital_managment.model.Appointment;
import com.nexgen.sanjeevani.hospital_managment.model.Patient;
import com.nexgen.sanjeevani.hospital_managment.repository.AppointmentRepository;
import com.nexgen.sanjeevani.hospital_managment.repository.DoctorRepository;
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
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

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
    public PatientResponseDto loginPatient(String userName, String typedPassword) {
        Optional<Patient> patient = patientRepository.findByUserName(userName);
        if(patient.isPresent()){
            if(patient.get().getPassword().equals(typedPassword)){
                Patient patientEntity = patient.get();
                PatientResponseDto patientResponseDto = new PatientResponseDto();
                patientResponseDto.setId(patientEntity.getPatientId());
                patientResponseDto.setUserName(patientEntity.getUserName());
                patientResponseDto.setFirstName(patientEntity.getFirstName());
                patientResponseDto.setLastName(patientEntity.getLastName());
                patientResponseDto.setEmail(patientEntity.getEmail());
                patientResponseDto.setPhone(patientEntity.getPhone());
                patientResponseDto.setGender(Gender.valueOf(patientEntity.getGender()));
                return patientResponseDto;
            } else {
                throw new RuntimeException("Invalid Password");
            }
        }else {
            throw new RuntimeException("Invalid UserName");
        }
    }

    @Override
    public PatientResponseDto updatePatient(PatientDto patient) {
       //DTO to Entity
        Patient patientEntity = new Patient();
        patientEntity.setPatientId(patient.getId());
        patientEntity.setUserName(patient.getUserName());
        patientEntity.setPassword(patient.getPassword());
        patientEntity.setFirstName(patient.getFirstName());
        patientEntity.setLastName(patient.getLastName());
        patientEntity.setEmail(patient.getEmail());
        patientEntity.setPhone(patient.getPhone());
        patientEntity.setGender(patient.getGender().name());

        //Saved the entity
        Patient savedPatientData = patientRepository.save(patientEntity);

        //Entity to dto
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(savedPatientData.getPatientId());
        patientResponseDto.setUserName(savedPatientData.getUserName());
        patientResponseDto.setFirstName(savedPatientData.getFirstName());
        patientResponseDto.setLastName(savedPatientData.getLastName());
        patientResponseDto.setEmail(savedPatientData.getEmail());
        patientResponseDto.setPhone(savedPatientData.getPhone());
        patientResponseDto.setGender(Gender.valueOf(savedPatientData.getGender()));

        return patientResponseDto;
    }


    @Override
    public AppointmentResponseDto addAppointment(AppointmentRequestDto appointment, Long patientId) {
        //Fetch the patient details
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient Not Found"));

        //Creating a dummy doctor with sepality as GENERAL
        Doctor doctor = new Doctor();
        doctor.setUserName("Doctor1");
        doctor.setPassword("Doc@1234");
        doctor.setName("Dr.Nirman");
        doctor.setSpeciality("GENERAL");
        doctor.setPhone("7845123698");
        Doctor savedDoctor = doctorRepository.save(doctor);

        Appointment appointmentEntity = new Appointment();
        appointmentEntity.setAppointmentStatus("APPROVED");
        appointmentEntity.setRequestedAppointmentDate(appointment.getAppointmentDate());
        appointmentEntity.setScheduledAppointmentDate(appointment.getAppointmentDate().plusHours(2));
        appointmentEntity.setSpeciality("GENERAL");
        appointmentEntity.setDoctor(savedDoctor);
        appointmentEntity.setPatient(patient);

        //Create the symtoms
        List<SymtomDto> symtoms = appointment.getSymtoms();
        List<Symtom> symtomList = new ArrayList<>();
        for(SymtomDto symtom: symtoms){
            Symtom symtom1 = new Symtom();
            symtom1.setDescription(symtom.getDescription());
            symtom1.setSeverity(symtom.getSeverity());
            symtomList.add(symtom1);
        }

        appointmentEntity.setSymtoms(symtomList);

        Appointment savedAppointment = appointmentRepository.save(appointmentEntity);

        AppointmentResponseDto appointmentResponseDto = new AppointmentResponseDto();
        appointmentResponseDto.setId(savedAppointment.getAppointmentId());
        appointmentResponseDto.setAppointmentStatus(savedAppointment.getAppointmentStatus());
        appointmentResponseDto.setScheduledAppointmentDate(savedAppointment.getScheduledAppointmentDate());
        appointmentResponseDto.setSpeciality(savedAppointment.getSpeciality());
        appointmentResponseDto.setDoctorName(savedAppointment.getDoctor().getName());
        appointmentResponseDto.setPatientName(savedAppointment.getPatient().getFirstName() + " " + savedAppointment.getPatient().getLastName());

        List<SymtomDto> symtomDtoList = new ArrayList<>();
        for(Symtom symtom: savedAppointment.getSymtoms()){
            SymtomDto symtomDto = new SymtomDto();
            symtomDto.setDescription(symtom.getDescription());
            symtomDto.setSeverity(symtom.getSeverity());
            symtomDtoList.add(symtomDto);
        }

        appointmentResponseDto.setSymtoms(symtomDtoList);

        return appointmentResponseDto;
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
    public AppointmentResponseDto updateAppointment(AppointmentRequestDto appointment, Long patientId) {
        //TODO COMPLETE this
        return null;
    }
}

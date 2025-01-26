package com.nexgen.sanjeevani.hospital_managment.repository;

import com.nexgen.sanjeevani.hospital_managment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByUserName(String userName);
}

package com.nexgen.sanjeevani.hospital_managment.repository;

import com.nexgen.sanjeevani.hospital_managment.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

package com.nexgen.sanjeevani.hospital_managment.repository;

import com.nexgen.sanjeevani.hospital_managment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.patient.patientId = :id")
    List<Appointment> findByPatientId(@Param("id")Long patientId);
}

package com.nexgen.sanjeevani.hospital_managment.repository;

import com.nexgen.sanjeevani.hospital_managment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

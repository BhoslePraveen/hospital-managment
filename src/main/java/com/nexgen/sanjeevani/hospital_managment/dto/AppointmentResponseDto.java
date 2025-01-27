package com.nexgen.sanjeevani.hospital_managment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class AppointmentResponseDto {
    private Long id;
    private String appointmentStatus;
    private LocalDateTime scheduledAppointmentDate;
    private String speciality;
    private String doctorName;
    private String patientName;
    private List<SymtomDto> symtoms;
}

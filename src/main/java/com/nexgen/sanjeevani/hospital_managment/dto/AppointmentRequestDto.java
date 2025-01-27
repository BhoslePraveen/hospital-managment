package com.nexgen.sanjeevani.hospital_managment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AppointmentRequestDto {
    private Long id;

    @NotNull
    private LocalDateTime appointmentDate;

    @NotNull
    private String speciality;

    @NotNull
    @Size(min = 1)
    private List<SymtomDto> symtoms;
}

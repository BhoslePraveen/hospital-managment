package com.nexgen.sanjeevani.hospital_managment.dto;

import com.nexgen.sanjeevani.hospital_managment.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientResponseDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Gender gender;
}

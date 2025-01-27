package com.nexgen.sanjeevani.hospital_managment.dto;

import com.nexgen.sanjeevani.hospital_managment.enums.Gender;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
public class PatientDto {

    private Long id;

    @NotNull(message = "UserName cannot be null")
    @Length(min = 4, max = 10, message = "UserName must be between 3 and 15 characters")
    private String userName;

    @NotNull(message = "Password cannot be null")
    @Length(min = 4, max = 10, message = "Password must be between 6 and 15 characters")
    private String password;

    @NotNull(message = "First Name cannot be null")
    @Length(min = 4, message = "First Name must be between 3 and 15 characters")
    private String firstName;
    private String lastName;
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Length(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;
}

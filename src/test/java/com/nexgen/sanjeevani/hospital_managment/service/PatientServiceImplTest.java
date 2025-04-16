package com.nexgen.sanjeevani.hospital_managment.service;

import java.time.LocalDateTime;

import com.nexgen.sanjeevani.hospital_managment.dto.PatientResponseDto;
import com.nexgen.sanjeevani.hospital_managment.enums.Gender;

import com.nexgen.sanjeevani.hospital_managment.dto.PatientDto;
import com.nexgen.sanjeevani.hospital_managment.model.Patient;
import com.nexgen.sanjeevani.hospital_managment.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;
    @Mock
    private PatientRepository patientRepository;

    @Test
    public void testRegisterPatient() {
        //Given
        //These are our dummy Objects
        PatientDto patientDto = new PatientDto();
        patientDto.setId(101L);
        patientDto.setUserName("vikas101");
        patientDto.setPassword("123abc");
        patientDto.setFirstName("vikas");
        patientDto.setLastName("bhatia");
        patientDto.setEmail("vikas@gmail.com");
        patientDto.setPhone("1234567890");
        patientDto.setGender(Gender.MALE);

        //These are our dummy Objects
        Patient patient = new Patient();
        patient.setPatientId(101L);
        patient.setUserName("vikas101");
        patient.setPassword("123abc");
        patient.setFirstName("vikas");
        patient.setLastName("bhatia");
        patient.setEmail("vikas@gmail.com");
        patient.setPhone("1234567890");
        patient.setGender("MALE");

        //When
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
        String output = patientService.registerPatient(patientDto);

        //Then
        Assertions.assertNotNull(output);
        Assertions.assertEquals("Patient Registered Successfully", output);
    }

    @Test
    public void testUpdatePatient(){
        //Given
        PatientDto patientDto = new PatientDto();
        patientDto.setId(101L);
        patientDto.setUserName("vikas101");
        patientDto.setPassword("123abc");
        patientDto.setFirstName("vikas");
        patientDto.setLastName("bhatia");
        patientDto.setEmail("vikas@gmail.com");
        patientDto.setPhone("1234567890");
        patientDto.setGender(Gender.MALE);

        //These are our dummy Objects
        Patient patient = new Patient();
        patient.setPatientId(101L);
        patient.setUserName("vikas101");
        patient.setPassword("123abc");
        patient.setFirstName("vikas");
        patient.setLastName("bhatia");
        patient.setEmail("vikas@gmail.com");
        patient.setPhone("1234567890");
        patient.setGender("MALE");

        //When
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
        PatientResponseDto result = patientService.updatePatient(patientDto);

        //Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals("vikas",result.getFirstName());
        Assertions.assertEquals(101L,result.getId());

    }
}
#test
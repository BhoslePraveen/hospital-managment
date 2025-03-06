package com.nexgen.sanjeevani.hospital_managment.service.security;

import com.nexgen.sanjeevani.hospital_managment.model.Patient;
import com.nexgen.sanjeevani.hospital_managment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Connecting to the patient repo to get the user details using username
        Patient patient = patientRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with given name is not found"));

        return new User(patient.getUsername(),
                patient.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }
}

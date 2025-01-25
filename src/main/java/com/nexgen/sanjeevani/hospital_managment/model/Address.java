package com.nexgen.sanjeevani.hospital_managment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address extends AuditStamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private String country;
    @ManyToOne
    private Patient patient;
}

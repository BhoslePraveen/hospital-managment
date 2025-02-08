package com.nexgen.sanjeevani.hospital_managment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "symtoms")
public class Symtom extends AuditStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descp")
    private String description;

    @Column(name = "severity")
    private String severity;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}

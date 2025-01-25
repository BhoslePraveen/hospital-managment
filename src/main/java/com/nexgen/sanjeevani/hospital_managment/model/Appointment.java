package com.nexgen.sanjeevani.hospital_managment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "appointments")
public class Appointment extends AuditStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private Long appointmentId;

    @Column(name = "status")
    private String appointmentStatus;

    @Column(name = "app_date")
    private LocalDateTime appointmentDate;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private Patient patient;

    @OneToMany(mappedBy = "appointment" )
    private List<Symtom> symtoms;
}

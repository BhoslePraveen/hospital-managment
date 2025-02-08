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

    @Column(name = "request_app_date")
    private LocalDateTime requestedAppointmentDate;

    @Column(name = "scheduled_app_date")
    private LocalDateTime scheduledAppointmentDate;

    @Column(name = "speciality")
    private String speciality;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private Patient patient;

    //Cascase is used to transfer the parent class action i.e save to save and delete to delete
    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL )
    private List<Symtom> symtoms;
}

package com.nexgen.sanjeevani.hospital_managment.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditStamp {

    @CreationTimestamp
    @Column(updatable = false,name = "created_on",nullable = false)
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "modified_on",nullable = false)
    private LocalDateTime modifiedOn;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;
}

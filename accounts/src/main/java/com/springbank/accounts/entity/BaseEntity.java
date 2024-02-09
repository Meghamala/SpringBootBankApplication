package com.springbank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// to indicate it to be superclass
@MappedSuperclass
// to avoid writing getter setter methods, annotations of lombork. all get set methods will be generated
@Getter
@Setter
@ToString
// for Spring data JPA to perform auditing
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    // problem is spring data JPA can get date from server  or local system only and not user data
    @CreatedDate
    @Column(updatable = false) // to not to update create time during updates
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

}

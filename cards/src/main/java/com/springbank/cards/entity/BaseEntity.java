package com.springbank.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

    @Column(updatable = false) // to not to update create time during updates
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    private LocalDateTime updatesAt;

    @Column(insertable = false)
    private String updatedBy;
}

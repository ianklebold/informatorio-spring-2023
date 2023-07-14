package com.info.javajediprimerapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Publisher {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column(length = 100,columnDefinition = "varchar(100)",updatable = true,nullable = false)
    private String name;

    @Column(length = 70,columnDefinition = "varchar(70)",updatable = true,nullable = false)
    private String address;

    @Column(length = 70,columnDefinition = "varchar(70)",updatable = true,nullable = false)
    private String city;

    @Column(length = 70,columnDefinition = "varchar(70)",updatable = true,nullable = false)
    private String country;

    @Column(length = 15,columnDefinition = "varchar(15)",updatable = true,nullable = false)
    private String cellphone;

    @Column(length = 30,columnDefinition = "varchar(30)",updatable = true,nullable = false)
    private String webSite;

}

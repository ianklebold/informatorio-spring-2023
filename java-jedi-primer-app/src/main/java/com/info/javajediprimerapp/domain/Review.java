package com.info.javajediprimerapp.domain;

import com.info.javajediprimerapp.enumeration.CalificationEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Review {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column(length = 50,columnDefinition = "varchar(50)",updatable = true,nullable = false,unique = true)
    private String title;

    @Column(length = 255,columnDefinition = "varchar(255)",updatable = true,nullable = false,unique = true)
    private String content;

    @Enumerated(EnumType.STRING)
    private CalificationEnum calification;

    private LocalDateTime dateOfCreation;

    @ManyToOne
    private Book book;
}

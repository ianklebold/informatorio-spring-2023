package com.info.javajediprimerapp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
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

    @Column(length = 15,columnDefinition = "varchar(15)",updatable = true,nullable = false)
    private String cellphone;

    @Column(length = 30,columnDefinition = "varchar(30)",updatable = true,nullable = false)
    private String webSite;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @Builder.Default
    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();
}

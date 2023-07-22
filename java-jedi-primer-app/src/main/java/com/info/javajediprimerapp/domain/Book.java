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
public class Book {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36,columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID uuid;

    @Column(length = 100,columnDefinition = "varchar(100)",updatable = true,nullable = false)
    private String title;

    @ManyToOne
    private Author author;

    @Column(unique = true)
    private String isbn;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    private Publisher publisher;

    private int numberPages;

    @ManyToMany
    @JoinTable(name = "book_category",joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        publisher.getBooks().add(this);
    }

    public void setAuthor(Author author) {
        this.author = author;
        author.getBooks().add(this);
    }

    public void addCategories(Category category){
        if (this.categories == null){
            this.categories = new ArrayList<>();
        }
        this.categories.add(category);
    }

    @Override
    public String toString() {
        return "Book{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", numberPages=" + numberPages +
                '}';
    }
}

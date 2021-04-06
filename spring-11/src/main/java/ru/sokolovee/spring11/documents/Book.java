package ru.sokolovee.spring11.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "book")
public class Book {
    @Id
    private long id;
    private String name;
    @DBRef
    private Author author;
    @DBRef
    private Genre genre;

    public Book(long id) {
        this.id = id;
    }
}

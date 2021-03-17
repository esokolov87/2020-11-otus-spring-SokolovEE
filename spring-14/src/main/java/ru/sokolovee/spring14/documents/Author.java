package ru.sokolovee.spring14.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "author")
public class Author {
    @Id
    private long id;
    private String name;

    public Author(long id) {
        this.id = id;
    }
}

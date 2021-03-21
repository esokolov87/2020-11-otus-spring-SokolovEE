package ru.sokolovee.spring14.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "genre")
public class Genre {
    @Id
    private long id;
    private String name;

    public Genre(long id) {
        this.id = id;
    }
}

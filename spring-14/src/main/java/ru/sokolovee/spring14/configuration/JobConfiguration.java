package ru.sokolovee.spring14.configuration;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.sokolovee.spring14.documents.Author;
import ru.sokolovee.spring14.documents.Book;
import ru.sokolovee.spring14.documents.Comment;
import ru.sokolovee.spring14.documents.Genre;
import ru.sokolovee.spring14.service.ConvertingService;

import javax.persistence.EntityManager;
import java.util.HashMap;


@Configuration
@RequiredArgsConstructor
public class JobConfiguration {
    private static final int CHUNK_SIZE = 5;
    private final Logger logger = LoggerFactory.getLogger("Batch");

    private final MongoTemplate mongoTemplate;
    private final EntityManager entityManager;
    private final ConvertingService convertingService;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public MongoItemReader<Book> readerBook() {
        return new MongoItemReaderBuilder<Book>()
                .name("bookItemReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(Book.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<Author> readerAuthor() {
        return new MongoItemReaderBuilder<Author>()
                .name("authorItemReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(Author.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<Genre> readerGenre() {
        return new MongoItemReaderBuilder<Genre>()
                .name("genreItemReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(Genre.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<Comment> readerComment() {
        return new MongoItemReaderBuilder<Comment>()
                .name("commentItemReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(Comment.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor processorBook(ConvertingService convertingService) {
        return (ItemProcessor<Book, ru.sokolovee.spring14.entities.Book>) convertingService::convertBook;
    }

    @StepScope
    @Bean
    public ItemProcessor processorAuthor(ConvertingService convertingService) {
        return (ItemProcessor<Author, ru.sokolovee.spring14.entities.Author>) convertingService::convertAuthor;
    }

    @StepScope
    @Bean
    public ItemProcessor processorGenre(ConvertingService convertingService) {
        return (ItemProcessor<Genre, ru.sokolovee.spring14.entities.Genre>) convertingService::convertGenre;
    }

    @StepScope
    @Bean
    public ItemProcessor processorComment(ConvertingService convertingService) {
        return (ItemProcessor<Comment, ru.sokolovee.spring14.entities.Comment>) convertingService::convertComment;
    }

    @StepScope
    @Bean
    public JpaItemWriter writer() {
        return new JpaItemWriterBuilder<>()
                .entityManagerFactory(entityManager.getEntityManagerFactory())
                .build();
    }

    @Bean
    public Job importData() {
        return jobBuilderFactory.get("importData")
                .start(stepAuthors())
                .next(stepGenres())
                .next(stepBooks())
                .next(stepComments())
                .build();
    }

    @Bean
    public Step stepBooks() {
        return stepBuilderFactory.get("stepBooks")
                .chunk(CHUNK_SIZE)
                .reader(readerBook())
                .processor(processorBook(convertingService))
                .writer(writer())
                .build();
    }

    @Bean
    public Step stepAuthors() {
        return stepBuilderFactory.get("stepAuthors")
                .chunk(CHUNK_SIZE)
                .reader(readerAuthor())
                .processor(processorAuthor(convertingService))
                .writer(writer())
                .build();
    }

    @Bean
    public Step stepGenres() {
        return stepBuilderFactory.get("stepGenres")
                .chunk(CHUNK_SIZE)
                .reader(readerGenre())
                .processor(processorGenre(convertingService))
                .writer(writer())
                .build();
    }

    @Bean
    public Step stepComments() {
        return stepBuilderFactory.get("stepComments")
                .chunk(CHUNK_SIZE)
                .reader(readerComment())
                .processor(processorComment(convertingService))
                .writer(writer())
                .build();
    }
}

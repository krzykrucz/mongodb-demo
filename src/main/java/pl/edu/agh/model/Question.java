package pl.edu.agh.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

/**
 * Created by hector on 04.12.16.
 */

@Getter
@Setter
@ToString
@Document(collection = Question.COLLECTION_NAME)
public class Question {

    public static final String COLLECTION_NAME = "Jeopardy";

    @Id
    private String id;
    private String category;
    @Field(value = "air_date")
    private LocalDate airDate;
    private String question;
    private String answer;
    private String round;
    private Money value;
    @Field(value = "show_number")
    private Integer showNumber;

}

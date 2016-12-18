package pl.edu.agh.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by hector on 17.12.16.
 */

@Getter
@Setter
@ToString
public class FieldStats {
    @Id
    private String id;
    @Field
    private int occurrence;
}

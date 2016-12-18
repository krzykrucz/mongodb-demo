package pl.edu.agh.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Year;

/**
 * Created by hector on 17.12.16.
 */

@Getter
@Setter
@ToString
public class QuestionsInYearStats {
    private Year id;
    private int value;
}

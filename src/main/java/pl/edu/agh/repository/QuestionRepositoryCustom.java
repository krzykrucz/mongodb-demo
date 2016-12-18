package pl.edu.agh.repository;

import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import pl.edu.agh.model.FieldStats;
import pl.edu.agh.model.Question;
import pl.edu.agh.model.QuestionsInYearStats;

import java.util.List;

/**
 * Created by hector on 04.12.16.
 */
public interface QuestionRepositoryCustom {

    List<FieldStats> findAggregatedFieldStats();
    MapReduceResults<QuestionsInYearStats> findQuestionsInYearStats();
    List<Question> findAllTop();
}

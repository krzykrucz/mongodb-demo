package pl.edu.agh.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import pl.edu.agh.model.FieldStats;
import pl.edu.agh.model.Question;

import java.util.List;

/**
 * Created by hector on 04.12.16.
 */
public interface QuestionRepositoryCustom {

    List<FieldStats> findAggregatedFieldStats(String fieldName, Criteria criteria);
    <T>MapReduceResults<T> findQuestionsInYearStats(String mapFunctionPath, String reduceFunctionPath, Class<T> clazz);
    List<Question> findAllTop(Sort sort, int top);
}

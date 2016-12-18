package pl.edu.agh.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pl.edu.agh.model.FieldStats;
import pl.edu.agh.model.Question;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

/**
 * Created by hector on 04.12.16.
 */
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;

    @Autowired
    public QuestionRepositoryImpl(MongoTemplate mongoTemplate, MongoOperations mongoOperations) {
        this.mongoTemplate = mongoTemplate;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<FieldStats> findAggregatedFieldStats(String fieldName, Criteria matchCriteria) {
        TypedAggregation<Question> aggregation = newAggregation(
                Question.class,
                Aggregation.match(Criteria.where(fieldName).elemMatch(matchCriteria)),
                Aggregation.group(fieldName).count().as("occurrence")
        );
        AggregationResults<FieldStats> results = mongoTemplate.aggregate(aggregation, FieldStats.class);

        return results.getMappedResults();
    }

    @Override
    public <T>MapReduceResults<T> findQuestionsInYearStats(String mapFunctionPath, String reduceFunctionPath, Class<T> clazz) {
        return mongoOperations.mapReduce(
                Question.COLLECTION_NAME,
                mapFunctionPath,
                reduceFunctionPath,
                clazz);
    }

    @Override
    public List<Question> findAllTop(Sort sort, int top) {
        Query query = new Query();
        query.limit(top);
        query.with(sort);

        return mongoOperations.find(query, Question.class);
    }
}

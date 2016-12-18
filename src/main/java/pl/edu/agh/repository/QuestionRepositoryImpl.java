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
import pl.edu.agh.model.QuestionsInYearStats;

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
    public List<FieldStats> findAggregatedFieldStats() {
        TypedAggregation<Question> aggregation = newAggregation(
                Question.class,
                Aggregation.match(Criteria.where("category").regex("^A")),
                Aggregation.group("category").count().as("occurrence")
        );
        AggregationResults<FieldStats> results = mongoTemplate.aggregate(aggregation, FieldStats.class);

        return results.getMappedResults();
    }

    @Override
    public MapReduceResults<QuestionsInYearStats> findQuestionsInYearStats() {
        return mongoOperations.mapReduce(
                Question.COLLECTION_NAME,
                "classpath:map.js",
                "classpath:reduce.js",
                QuestionsInYearStats.class);
    }

    @Override
    public List<Question> findAllTop() {
        Sort ascSortOnShowNumber = new Sort(Sort.Direction.ASC, "showNumber");
        Query query = new Query();
        query.limit(50);
        query.with(ascSortOnShowNumber);

        return mongoOperations.find(query, Question.class);
    }
}

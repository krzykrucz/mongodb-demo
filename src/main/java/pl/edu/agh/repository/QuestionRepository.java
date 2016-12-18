package pl.edu.agh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.agh.model.Question;

/**
 * Created by hector on 04.12.16.
 */

public interface QuestionRepository extends MongoRepository<Question, String>, QuestionRepositoryCustom {
}

package pl.edu.agh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.agh.repository.QuestionRepository;

@SpringBootApplication
public class MongodbApplication {

    @Autowired
    private QuestionRepository questionRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

}

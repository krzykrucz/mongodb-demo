package pl.edu.agh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.agh.repository.QuestionRepository;

import java.util.logging.Logger;

@SpringBootApplication
public class MongodbApplication implements CommandLineRunner {

    private final Logger LOGGER = Logger.getLogger("MongoDbApplication");

    @Autowired
    private QuestionRepository questionRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        questionRepository.findAggregatedFieldStats()
                .forEach(o -> LOGGER.info(o.toString()));

        questionRepository.findQuestionsInYearStats()
                .forEach(o -> LOGGER.info(o.toString()));

        questionRepository.findAllTop()
                .forEach(o -> LOGGER.info(o.toString()));
    }

}

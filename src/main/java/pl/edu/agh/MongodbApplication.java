package pl.edu.agh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import pl.edu.agh.model.QuestionsInYearStats;
import pl.edu.agh.repository.QuestionRepository;

@SpringBootApplication
public class MongodbApplication /*implements CommandLineRunner*/ {

    @Autowired
    private QuestionRepository questionRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//        Criteria startsWithLetterA = (new Criteria()).regex("^A");
//        questionRepository.findAggregatedFieldStats("category", startsWithLetterA)
//                .forEach(System.out::println);
//
//        questionRepository.findQuestionsInYearStats("classpath:map.js", "classpath:reduce.js", QuestionsInYearStats.class)
//                .forEach(System.out::println);
//
//
//        Sort ascSortOnShowNumber = new Sort(Sort.Direction.ASC, "showNumber");
//        questionRepository.findAllTop(ascSortOnShowNumber, 5000)
//                .forEach(System.out::println);
//    }

}

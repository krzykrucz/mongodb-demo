package pl.edu.agh.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.model.FieldStats;
import pl.edu.agh.model.Question;
import pl.edu.agh.model.QuestionsInYearStats;
import pl.edu.agh.repository.QuestionRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by hector on 17.12.16.
 */

@RestController("/mongo")
public class MongoController {

    private final QuestionRepository questionRepository;

    @Autowired
    public MongoController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/aggregation")
    public String getAggregationResults(Model model) {
        Criteria startsWithLetterA = (new Criteria()).regex("^A");
        List<FieldStats> results = questionRepository.findAggregatedFieldStats("category", startsWithLetterA);
        model.addAttribute("results", results);

        return "aggregation";
    }

    @GetMapping("/mapreduce")
    public String getMapReduceResults(Model model) {
        Iterator<QuestionsInYearStats> mapReduceResultIt = questionRepository
                .findQuestionsInYearStats(
                        "classpath:map.js",
                        "classpath:reduce.js",
                        QuestionsInYearStats.class)
                .iterator();

        List<QuestionsInYearStats> results = StreamSupport
                .stream(Spliterators
                        .spliteratorUnknownSize(mapReduceResultIt, Spliterator.ORDERED), false)
                .collect(Collectors.toList());
        model.addAttribute("results", results);

        return "mapreduce";
    }

    @GetMapping("/simple")
    public String getSimpleResults(Model model) {
        Sort ascSortOnShowNumber = new Sort(Sort.Direction.ASC, "showNumber");
        List<Question> results = questionRepository.findAllTop(ascSortOnShowNumber, 5000);
        model.addAttribute("results", results);

        return "simple";
    }

}
package pl.edu.agh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

@Controller
public class MongoController {

    private final QuestionRepository questionRepository;

    @Autowired
    public MongoController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping(value = {"/", "/mongo"})
    public String mongoView() {
        return "mongo";
    }

    @PostMapping("/mongo/aggregation")
    public String getAggregationResults(RedirectAttributes redirectAttributes) {
        Criteria startsWithLetterA = (new Criteria()).regex("^A");
        List<FieldStats> results = questionRepository.findAggregatedFieldStats("category", startsWithLetterA);
        redirectAttributes.addFlashAttribute("aggregationResults", results);

        return "redirect:/mongo?aggregation";
    }

    @PostMapping("/mongo/mapreduce")
    public String getMapReduceResults(RedirectAttributes redirectAttributes) {
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
        redirectAttributes.addFlashAttribute("mapReduceResults", results);

        return "redirect:/mongo?mapreduce";
    }

    @PostMapping("/mongo/simple")
    public String getSimpleResults(RedirectAttributes redirectAttributes) {
        Sort ascSortOnShowNumber = new Sort(Sort.Direction.ASC, "showNumber");
        List<Question> results = questionRepository.findAllTop(ascSortOnShowNumber, 5000);
        redirectAttributes.addFlashAttribute("simpleResults", results);

        return "redirect:/mongo?simple";
    }

}
package controller;

import model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.QuestionService;

import java.util.List;

/**
 * Created by CoT on 12/2/17.
 */
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(path = "/questions", method = RequestMethod.POST)
    public int addQuestion(@RequestBody Question question){
        Integer id = questionService.addQuestion(question);
        return id;
    }

    @RequestMapping(path = "/questions", method = RequestMethod.PUT)
    public int updateQuestion(@RequestBody Question question){
        questionService.updateQuestion(question);
        return question.getId();
    }

    @RequestMapping(path = "/questions", method = RequestMethod.GET)
    public List<Question> getQuestions(){
        return questionService.getAllQuestions();
    }

}

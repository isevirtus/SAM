package br.edu.ufcg.embedded.sam.controllers;

import br.edu.ufcg.embedded.sam.models.Question;
import br.edu.ufcg.embedded.sam.services.QuestionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mendelssohn on 07/06/17.
 */
@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionCtrl {
    /**
     * {@link QuestionService} for operations.
     */
    private final QuestionService questionService;

    @Autowired
    public QuestionCtrl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @ApiOperation("create")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Question> create(@RequestBody @Valid Question question) {

        question = questionService.create(question);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @ApiOperation("update")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Question> update(@RequestBody @Valid Question question) {
        questionService.update(question);
        return new ResponseEntity<>(question, HttpStatus.ACCEPTED);
    }

    @ApiOperation("list")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Question>> list() {
        List<Question> questions = questionService.findAll();
        return new ResponseEntity<>(questions, HttpStatus.ACCEPTED);
    }

    @ApiOperation("get-question")
    @GetMapping(value = "/get-question/{idQuestion}")
    public ResponseEntity<Question> getById(@PathVariable("idQuestion") Integer idQuestion) {
        Question question = questionService.find(idQuestion);
        return new ResponseEntity<Question>(question, HttpStatus.ACCEPTED);
    }

    @ApiOperation("delete")
    @DeleteMapping(value = "/delete/{idQuestion}")
    public ResponseEntity<String> delete(@PathVariable("idQuestion") Integer idQuestion) {
        questionService.removeById(idQuestion);
        return new ResponseEntity<>("Questão removida com sucesso", HttpStatus.ACCEPTED);
    }

}

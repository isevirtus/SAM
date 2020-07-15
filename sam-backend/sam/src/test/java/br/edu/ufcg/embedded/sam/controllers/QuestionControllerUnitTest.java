package br.edu.ufcg.embedded.sam.controllers;

import br.edu.ufcg.embedded.sam.models.Metric;
import br.edu.ufcg.embedded.sam.models.Question;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by treinamento-sony on 28/06/17.
 */
public class QuestionControllerUnitTest {

    public static final String REST_SERVICE_URL = "http://localhost:8080/sam/api/question";

    @Test
    public void createQuestionsTest(){

        RestTemplate restTemplate = new RestTemplate();

        List<Metric> listMetrics = new ArrayList<>();
        Metric metric2 = new Metric("description", "baselineHypothesis");

        //Criando Question1
        Question question1 = new Question("Question1", listMetrics);
        question1.setId(1);
        URI uri1 = restTemplate.postForLocation(REST_SERVICE_URL + "/create", question1, Question.class);

        //Criando Question2
        Question question2 = new Question("Question2", listMetrics);
        question1.setId(2);
        URI uri2 = restTemplate.postForLocation(REST_SERVICE_URL + "/create", question1, Question.class);

        //Criando Question3
        Question question3 = new Question("Question3", listMetrics);
        question1.setId(3);
        URI uri3 = restTemplate.postForLocation(REST_SERVICE_URL + "/create", question1, Question.class);

        //Verificando se as metricas foram criadas com sucesso
        ArrayList<Metric> listQuestion = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        Assert.assertEquals(3, listQuestion.size());
    }

    @Test
    public void deleteQuestionsTest(){

        RestTemplate restTemplate = new RestTemplate();

        //Deletando question1
        restTemplate.delete(REST_SERVICE_URL + "/delete/1");

        //Deletando question2
        restTemplate.delete(REST_SERVICE_URL + "/delete/2");

        //Deletando question3
        restTemplate.delete(REST_SERVICE_URL + "/delete/3");

        //Verificando se as questoes foram deletadas com sucesso
        ArrayList<Metric> listQuestions = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        Assert.assertEquals(0, listQuestions.size());
    }

    // Test Create, edit and delete
    @Test
    public void test1(){

        RestTemplate restTemplate = new RestTemplate();

        List<Metric> listMetrics = new ArrayList<>();
        Metric metric2 = new Metric("description", "baselineHypothesis");

        //Criando Question1
        Question question1 = new Question("Question1", listMetrics);
        question1.setId(1);
        URI uri1 = restTemplate.postForLocation(REST_SERVICE_URL + "/create", question1, Question.class);

        //Verificando questao criada
        Question getQuestion = restTemplate.getForObject(REST_SERVICE_URL + "/get-question/1", Question.class);
        Assert.assertEquals(question1.getQuestion(), getQuestion.getQuestion());

        //Editando questao
        Question updateQuestion = new Question("Question1 editada", listMetrics);
        updateQuestion.setId(1);
        restTemplate.put(REST_SERVICE_URL + "/update", updateQuestion);

        //Verificando questao editada
        Question getQuestion2 = restTemplate.getForObject(REST_SERVICE_URL + "/get-question/1", Question.class);
        Assert.assertEquals(updateQuestion.getQuestion(), getQuestion2.getQuestion());

        //Deletando Question
        restTemplate.delete(REST_SERVICE_URL + "/delete/1");

        //Verificando se a questao foi deletada com sucesso
        ArrayList<Metric> listQuestions = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        Assert.assertEquals(0, listQuestions.size());
    }




}

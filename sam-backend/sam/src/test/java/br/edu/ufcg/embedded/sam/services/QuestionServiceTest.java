package br.edu.ufcg.embedded.sam.services;

import br.edu.ufcg.embedded.sam.models.Metric;
import br.edu.ufcg.embedded.sam.models.Question;
import br.edu.ufcg.embedded.sam.repositories.MetricRepository;
import br.edu.ufcg.embedded.sam.repositories.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.mockito.Mockito.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QuestionServiceTest.QuestionServiceTestContextConfiguration.class)
public class QuestionServiceTest {


    @Configuration
    static class QuestionServiceTestContextConfiguration {

        @Bean
        public QuestionService questionService(){
            return new QuestionService(questionRepository(), metricService());
        }

        @Bean
        public QuestionRepository questionRepository() {return mock(QuestionRepository.class);};

        @Bean
        public MetricService metricService(){
            return new MetricService(metricRepository());
        }

        @Bean
        public MetricRepository metricRepository() {return mock(MetricRepository.class);};

    }

    @Autowired
    private QuestionRepository questionRepository;



    @Autowired
    private QuestionService questionService;


    private Question question;

    @Before
    public void setupMock(){

        question = new Question();

    }

    @Test
    public void testCreate(){


        when(questionRepository.save(question)).thenReturn(question);
        System.out.println(questionService.findAll());
        Assert.assertEquals(questionService.createOrUpdate(question), question);

        verify(questionRepository).save(question);

    }

    @Test
    @SuppressWarnings("Duplicates")
    public void testRemoveNotNull(){
        int idQuestion= 1;


        when(questionRepository.findOne(idQuestion)).thenReturn(question);
        when(questionRepository.exists(idQuestion)).thenReturn(true);

        Assert.assertEquals(true , questionService.removeById(idQuestion));

        verify(questionRepository).delete(idQuestion);

    }

    @Test
    public void testRemoveNull(){
        int idQuestion = 1;


        when(questionRepository.findOne(idQuestion)).thenReturn(null);
        Assert.assertEquals(false , questionService.removeById(idQuestion));


    }


}

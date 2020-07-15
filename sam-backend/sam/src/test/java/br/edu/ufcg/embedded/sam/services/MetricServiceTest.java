package br.edu.ufcg.embedded.sam.services;

import br.edu.ufcg.embedded.sam.models.Metric;
import br.edu.ufcg.embedded.sam.repositories.MetricRepository;
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
@ContextConfiguration(classes = MetricServiceTest.MetricServiceTestContextConfiguration.class)
public class MetricServiceTest {


    @Configuration
    static class MetricServiceTestContextConfiguration {

        @Bean
        public MetricService metricService(){
            return new MetricService(metricRepository());
        }

        @Bean
        public MetricRepository metricRepository() {return mock(MetricRepository.class);};

    }

    @Autowired
    private MetricRepository metricRepository;



    @Autowired
    private MetricService metricService;


    private Metric metric;

    @Before
    public void setupMock(){

        metric = new Metric("description",  "baselineHypothesis");

    }

    @Test
    public void testCreate(){


        when(metricRepository.save(metric)).thenReturn(metric);
        System.out.println(metricService.findAll());
        Assert.assertEquals(metricService.createOrUpdate(metric), metric);

        verify(metricRepository).save(metric);

    }

    @Test
    public void testRemoveNull(){
        int idmetric = 1;


        when(metricRepository.findOne(idmetric)).thenReturn(null);
        Assert.assertEquals(false , metricService.removeById(idmetric));


    }

    @Test
    @SuppressWarnings("Duplicates")
    public void testRemoveNotNull(){
        int idMetric= 1;


        when(metricRepository.findOne(idMetric)).thenReturn(metric);
        when(metricRepository.exists(idMetric)).thenReturn(true);

        Assert.assertEquals(true , metricService.removeById(idMetric));

        verify(metricRepository).delete(idMetric);

    }


}

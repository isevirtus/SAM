package br.edu.ufcg.embedded.sam.controllers;


import br.edu.ufcg.embedded.sam.models.Metric;
import org.apache.tomcat.jni.Status;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by treinamento-sony on 26/06/17.
 */


public class MetricControllerUnitTest {

    public static final String REST_SERVICE_URL = "http://localhost:8080/sam/api/project/metric";


    /* GET */
    @Test
    public void listMetrics(){
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<Metric> listMetrics = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        System.out.println(listMetrics);
    }


    /* GET */
    @Test
    public void getMetric(){

        RestTemplate restTemplate = new RestTemplate();
        Metric metric = restTemplate.getForObject(REST_SERVICE_URL + "/1", Metric.class);
        Assert.assertEquals("metric 19969ojyrtjkgtrhjfjk", metric.getName());
    }

    /*POST*/
    @Test
    public void createMetric(){

        RestTemplate restTemplate = new RestTemplate();
        Metric metric = new Metric("description", "baselineHypothesis");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URL + "/create", metric, Metric.class);
    }

    /*PUT*/
    @Test
    public void updateMetric(){
        RestTemplate restTemplate = new RestTemplate();
        Metric metric = new Metric("description editada", "baselineHypothesis");
        metric.setId(4);
        restTemplate.put(REST_SERVICE_URL + "/update", metric);
    }

    /*DELETE*/
    @Test
    public void deleteMetric(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URL + "/delete/3");
    }

    /*Criar, Editar e Deletar metrica*/
    @Test
    public void test1(){

        RestTemplate restTemplate = new RestTemplate();

        //Criando metrica
        Metric metric = new Metric("description", "baselineHypothesis");
        metric.setId(1);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URL + "/create", metric, Metric.class);

        //Verificando metrica criada
        Metric getMetric = restTemplate.getForObject(REST_SERVICE_URL + "/1", Metric.class);
        Assert.assertEquals(metric.getDescription(), getMetric.getDescription());

        //Editando metrica
        Metric updateMetric = new Metric("description editada", "baselineHypothesis");
        updateMetric.setId(1);
        restTemplate.put(REST_SERVICE_URL + "/update", updateMetric);

        //Verificando metrica editada
        Metric getMetric2 = restTemplate.getForObject(REST_SERVICE_URL + "/1", Metric.class);
        Assert.assertEquals(updateMetric.getDescription(), getMetric2.getDescription());

        //Deletando metrica
        restTemplate.delete(REST_SERVICE_URL + "/delete/1");

        //Verificando se a metrica foi deletada com sucesso
        ArrayList<Metric> listMetrics = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        Assert.assertEquals(0, listMetrics.size());
    }

    @Test
    public void createMetricsTest(){

        RestTemplate restTemplate = new RestTemplate();

        //Criando metrica1
        Metric metric1 = new Metric("description", "baselineHypothesis");
        metric1.setId(1);
        URI uri1 = restTemplate.postForLocation(REST_SERVICE_URL + "/create", metric1, Metric.class);

        //Criando metrica2
        Metric metric2 = new Metric("description", "baselineHypothesis");
        metric2.setId(2);
        URI uri2 = restTemplate.postForLocation(REST_SERVICE_URL + "/create", metric2, Metric.class);

        //Criando metrica3
        Metric metric3 = new Metric("description", "baselineHypothesis");
        metric3.setId(3);
        URI uri3 = restTemplate.postForLocation(REST_SERVICE_URL + "/create", metric3, Metric.class);

        //Verificando se as metricas foram criadas com sucesso
        ArrayList<Metric> listMetrics = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        Assert.assertEquals(3, listMetrics.size());
    }

    @Test
    public void deleteMetricsTest(){

        RestTemplate restTemplate = new RestTemplate();

        //Deletando metrica1
        restTemplate.delete(REST_SERVICE_URL + "/delete/1");

        //Deletando metrica2
        restTemplate.delete(REST_SERVICE_URL + "/delete/2");

        //Deletando metrica3
        restTemplate.delete(REST_SERVICE_URL + "/delete/3");

        //Verificando se a metrica foi deletada com sucesso
        ArrayList<Metric> listMetrics = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        Assert.assertEquals(0, listMetrics.size());


    }

    @Test
    public void createInvalidMetricTest(){

        RestTemplate restTemplate = new RestTemplate();

        //Criando metrica1
        Metric metric1 = new Metric(null, "baselineHypothesis");
        metric1.setId(1);
        URI uri1 = restTemplate.postForLocation(REST_SERVICE_URL + "/create", metric1, Metric.class);

        //Verificando metrica criada
        Metric getMetric = restTemplate.getForObject(REST_SERVICE_URL + "/1", Metric.class);
        Assert.assertEquals(metric1.getDescription(), getMetric.getDescription());

        //Criando metrica2
        Metric metric2 = new Metric("description", null);
        metric2.setId(2);
        URI uri2 = restTemplate.postForLocation(REST_SERVICE_URL + "/create", metric2, Metric.class);

        //Verificando metrica criada
        Metric getMetric2 = restTemplate.getForObject(REST_SERVICE_URL + "/2", Metric.class);
        Assert.assertEquals(metric2.getDescription(), getMetric.getDescription());

    }

}

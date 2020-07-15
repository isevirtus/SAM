package br.edu.ufcg.embedded.sam.controllers;

import br.edu.ufcg.embedded.sam.models.Objective;
import br.edu.ufcg.embedded.sam.models.Project;
import br.edu.ufcg.embedded.sam.models.Question;
import br.edu.ufcg.embedded.sam.models.Role;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by treinamento-sony on 28/06/17.
 */
public class ObjectiveControllerUnitTest {

    public static final String REST_SERVICE_URL = "http://localhost:8080/sam/api/project/objective";

    RestTemplate restTemplate = new RestTemplate();

    //Criando projeto
    Project project1 = new Project("name","function", 5,  10, new HashMap<Role,Integer>(), null,  "projectType", new ArrayList<Objective>());
    URI uri = restTemplate.postForLocation("http://localhost:8080/sam/api/project" + "/create", project1, Project.class);

    @Test
    public void createObjectiveTest(){

        RestTemplate restTemplate = new RestTemplate();

        //Criando objetivo1
        Objective objective1 = new Objective("objectsOfStudy1", "purpose1", "viewPoint1", "qualityFocus1", new ArrayList<>());
        objective1.setId(1);
        URI uri1 = restTemplate.postForLocation(REST_SERVICE_URL + "/create/1", objective1, Objective.class);

        //Criando objetivo2
        Objective objective2 = new Objective("objectsOfStudy2", "purpose2", "viewPoint2", "qualityFocus2", new ArrayList<>());
        objective2.setId(2);
        URI uri2 = restTemplate.postForLocation(REST_SERVICE_URL + "/create/1", objective2, Objective.class);

        //Verificando se os objetivos foram criados com sucesso
        ArrayList<Objective> listObjective = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        Assert.assertEquals(2, listObjective.size());
    }

    @Test
    public void deleteObjectiveTest(){

        RestTemplate restTemplate = new RestTemplate();

        //Deletando metrica1
        restTemplate.delete(REST_SERVICE_URL + "/delete/1/1");

        //Deletando metrica2
        restTemplate.delete(REST_SERVICE_URL + "/delete/1/2");

        //Verificando se os objetivos foram deletados com sucesso
        ArrayList<Objective> listObjective = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        Assert.assertEquals(0, listObjective.size());
    }

    // Test create, get, edit and delete objective
    @Test
    public void test1(){

        RestTemplate restTemplate = new RestTemplate();

        //Criando objetivo1
        Objective objective1 = new Objective("objectsOfStudy1", "purpose1", "viewPoint1", "qualityFocus1", new ArrayList<>());
        objective1.setId(1);
        URI uri1 = restTemplate.postForLocation(REST_SERVICE_URL + "/create/1", objective1, Objective.class);

        //Verificando metrica criada
        Objective getObjective = restTemplate.getForObject(REST_SERVICE_URL + "/get-objective/1", Objective.class);
        Assert.assertEquals("objectsOfStudy1", getObjective.getObjectOfStudy());

        //Editando metrica
        Objective updateObjective = new Objective("objectsOfStudy1 edit", "purpose1", "viewPoint1", "qualityFocus1", new ArrayList<>());
        updateObjective.setId(1);
        restTemplate.put(REST_SERVICE_URL + "/update", updateObjective);

        //Verificando metrica criada
        Objective getObjective2 = restTemplate.getForObject(REST_SERVICE_URL + "/get-objective/1", Objective.class);
        Assert.assertEquals("objectsOfStudy1 edit", getObjective2.getObjectOfStudy());

        //Deletando metrica1
        restTemplate.delete(REST_SERVICE_URL + "/delete/1/1");

        //Verificando se os objetivos foram deletados com sucesso
        ArrayList<Objective> listObjective = restTemplate.getForObject(REST_SERVICE_URL + "/list", ArrayList.class);
        Assert.assertEquals(0, listObjective.size());

    }

}

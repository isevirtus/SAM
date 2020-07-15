package br.edu.ufcg.embedded.sam.controllers;

import br.edu.ufcg.embedded.sam.models.Objective;
import br.edu.ufcg.embedded.sam.models.Project;
import br.edu.ufcg.embedded.sam.models.Question;
import br.edu.ufcg.embedded.sam.models.bayesiannetwork.Network;
import br.edu.ufcg.embedded.sam.models.bayesiannetwork.Node;
import br.edu.ufcg.embedded.sam.models.bayesiannetwork.ParentNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mendelssohn on 28/06/17.
 */
public class NetworkCtrlUnitTest {

    public static final String REST_NETWORK_SERVICE_URL = "http://localhost:8080/sam/api/network";
    public static final String REST_OBJECTIVE_SERVICE_URL = "http://localhost:8080/sam/api/project/objective";
    public static final String REST_PROJECT_SERVICE_URL = "http://localhost:8080/sam/api/project";
    private RestTemplate restTemplate;

    @Before
    public void initializing() {
        restTemplate = new RestTemplate();
        //Criando Projeto
        Project project = new Project("Projeto 1", "Função", 8, 5, new HashMap<>(2, 4), null, "Projeto tipo 3", new ArrayList<>());
        project.setId(1);

        URI uri1 = restTemplate.postForLocation(REST_PROJECT_SERVICE_URL + "/create", project, Project.class);

        //Criando Question1
        Question question1 = new Question("Question1", null);

        //Criando Question2
        Question question2 = new Question("Question2", null);

        //Criando Question3
        Question question3 = new Question("Question3", null);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        Objective objective = new Objective();
        objective.setId(1);
        objective.setName("Objetivo 1");
        objective.setObjectOfStudy("Estudo");
        objective.setPurpose("Proposito");
        objective.setQualityFocus("Foco de Qualidade");
        objective.setViewPoint("Ponto de vista");
        objective.setQuestions(questions);
        URI uri2 = restTemplate.postForLocation(REST_OBJECTIVE_SERVICE_URL + "/create/" + project.getId(), objective, Objective.class);

    }

    @Test
    public void createNetworkTest() {
        Objective objective = restTemplate.getForObject(REST_OBJECTIVE_SERVICE_URL + "/get-objective/1", Objective.class);

        Network result = restTemplate.getForObject(REST_NETWORK_SERVICE_URL + "/query/" + objective.getId(), Network.class);

        Assert.assertEquals(4, result.getNodes().size());
    }

    @Test
    public void testOfNodesAndParents() {
        Objective objective = restTemplate.getForObject(REST_OBJECTIVE_SERVICE_URL + "/get-objective/1", Objective.class);

        Network result = restTemplate.getForObject(REST_NETWORK_SERVICE_URL + "/query/" + objective.getId(), Network.class);

        for (Node node : result.getNodes()) {
            if (node.getParents().size() > 0) {
                for (ParentNode parent : node.getParents()) {
                    Assert.assertEquals(objective.getName(), parent.getName());
                }
            } else {
                Assert.assertEquals(node.getName(), objective.getName());
            }
        }
    }

}

package br.edu.ufcg.embedded.sam.services;
import br.edu.ufcg.embedded.sam.models.Objective;
import br.edu.ufcg.embedded.sam.models.Project;
import br.edu.ufcg.embedded.sam.repositories.ObjectiveRepository;
import br.edu.ufcg.embedded.sam.repositories.ProjectRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ObjectiveServiceTest.ObjectiveServiceTestContextConfiguration.class)
public class ObjectiveServiceTest {

    @Configuration
    static class ObjectiveServiceTestContextConfiguration {
        @Bean
        public ObjectiveService objectiveService() {
            return new ObjectiveService(objectiveRepository() ,projectService(),questionService() );
        }
        @Bean
        public ProjectService projectService() {
            return new ProjectService(projectRepository());
        }

        @Bean
        public ProjectRepository projectRepository() {
            return mock(ProjectRepository.class);
        }

        @Bean
        public ObjectiveRepository objectiveRepository() {
            return mock(ObjectiveRepository.class);
        }

        @Bean
        public QuestionService questionService() {
            return mock(QuestionService.class);
        }

    }

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ObjectiveService objectiveService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private QuestionService questionService;


    private Objective objective;

    private Project project;


    @Before
    public void setupMock(){

        objective = new Objective( "objectsOfStudy",  "purpose",  "viewPoint",  "qualityFocus", new ArrayList<>());
        project = new Project();
    }

    @Test
    public void testCreate(){
        int projectId = 1;

        //Arrange
        when(projectRepository.findOne(projectId)).thenReturn(project);
        when(objectiveRepository.save(objective)).thenReturn(objective);
        System.out.println(objectiveService.findAll());
        Assert.assertEquals(objectiveService.create(objective,projectId),objective);

        //Asserts
        verify(objectiveRepository).save(objective);
        verify(projectRepository).save(project);
    }

    @Test
    public void testRemoveNotNull(){
        int idObjective = 1;
        int idProject = 1;

        when(objectiveRepository.findOne(idObjective)).thenReturn(objective);
        when(projectRepository.findOne(idProject)).thenReturn(project);

        Assert.assertEquals(true , objectiveService.remove(idObjective,idProject));

        verify(objectiveRepository).delete(objective);
        verify(projectRepository).save(project);

    }

    @Test
    public void testRemoveNull(){
        int idObjective = 1;
        int idProject = 1;

        //If 2 are nulls
        when(objectiveRepository.findOne(idObjective)).thenReturn(null);
        when(projectRepository.findOne(idProject)).thenReturn(null);
        Assert.assertEquals(false , objectiveService.remove(idObjective,idProject));

        //If 1 is null
        when(objectiveRepository.findOne(idObjective)).thenReturn(objective);
        when(projectRepository.findOne(idProject)).thenReturn(null);
        Assert.assertEquals(false , objectiveService.remove(idObjective,idProject));

        when(objectiveRepository.findOne(idObjective)).thenReturn(null);
        when(projectRepository.findOne(idProject)).thenReturn(project);
        Assert.assertEquals(false , objectiveService.remove(idObjective,idProject));


    }
}
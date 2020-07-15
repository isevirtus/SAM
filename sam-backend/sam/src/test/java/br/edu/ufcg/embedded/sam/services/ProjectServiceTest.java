package br.edu.ufcg.embedded.sam.services;
import br.edu.ufcg.embedded.sam.repositories.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Created by treinamento-09 on 20/06/17.
 */
public class ProjectServiceTest {
    private ProjectRepository projectRepository;
    private ProjectService projectService;

    @Before
    public void setupMock(){
        projectRepository = mock(ProjectRepository.class);
        projectService = new ProjectService(projectRepository);
    }

    @Test
    public void testProjectService(){
        assertEquals(projectRepository,projectService.getRepository());
    }

}

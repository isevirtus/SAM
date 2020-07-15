package br.edu.ufcg.embedded.sam.services;

import br.edu.ufcg.embedded.sam.models.Project;
import br.edu.ufcg.embedded.sam.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Service for {@link Project}.
 */
@Service
public class ProjectService extends CrudService<Project> {

    private ProjectRepository repository;

    @Autowired
    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }
    
    protected JpaRepository<Project, Integer> getRepository() {
        return repository;
    }
}

package br.edu.ufcg.embedded.sam.repositories;

import br.edu.ufcg.embedded.sam.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link Project}.
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}

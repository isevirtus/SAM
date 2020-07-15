package br.edu.ufcg.embedded.sam.repositories;

import br.edu.ufcg.embedded.sam.models.Objective;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link Objective}.
 */
public interface ObjectiveRepository extends JpaRepository<Objective, Integer> {

}

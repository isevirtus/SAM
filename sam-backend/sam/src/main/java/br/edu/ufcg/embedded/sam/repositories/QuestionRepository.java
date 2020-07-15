package br.edu.ufcg.embedded.sam.repositories;

import br.edu.ufcg.embedded.sam.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repository for {@link Question}.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}

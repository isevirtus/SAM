package br.edu.ufcg.embedded.sam.repositories;

import br.edu.ufcg.embedded.sam.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link Metric}.
 */
public interface MetricRepository extends JpaRepository<Metric,Integer> {
}

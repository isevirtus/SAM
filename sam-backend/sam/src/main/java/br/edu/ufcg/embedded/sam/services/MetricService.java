package br.edu.ufcg.embedded.sam.services;

import br.edu.ufcg.embedded.sam.models.Metric;
import br.edu.ufcg.embedded.sam.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MetricService  extends CrudService<Metric> {

    private MetricRepository metricRepository;

    @Autowired
    public MetricService(MetricRepository repository) {
        this.metricRepository = repository;
    }

    @Override
    protected JpaRepository<Metric, Integer> getRepository() {
        return metricRepository;
    }
}

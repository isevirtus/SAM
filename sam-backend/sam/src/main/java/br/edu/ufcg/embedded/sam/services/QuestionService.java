package br.edu.ufcg.embedded.sam.services;

import br.edu.ufcg.embedded.sam.models.Metric;
import br.edu.ufcg.embedded.sam.models.Question;
import br.edu.ufcg.embedded.sam.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for {@link br.edu.ufcg.embedded.sam.models.Question}.
 */
@Service
public class QuestionService extends CrudService<Question> {

    private final QuestionRepository questionRepository;
    private final MetricService metricService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, MetricService metricService) {
        this.questionRepository = questionRepository;
        this.metricService = metricService;
    }



    public Question create(Question question) {
        attachMetrics(question);
        return getRepository().save(question);
    }

    private void attachMetrics(Question question) {
        List<Metric> newMetrics = new ArrayList<>();
        for (Metric metric : question.getMetrics()) {
            if (metric.getId() == null) {
                metricService.createOrUpdate(metric);
                newMetrics.add(metric);
            } else {
                newMetrics.add(metricService.find(metric.getId()));
            }
        }
        question.setMetrics(newMetrics);
    }

    public void update(Question question) {
        getRepository().save(question);
    }

    @Override
    protected JpaRepository<Question, Integer> getRepository() {
        return questionRepository;
    }
}

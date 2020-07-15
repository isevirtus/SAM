package br.edu.ufcg.embedded.sam.services;

import br.edu.ufcg.embedded.sam.exceptions.ValidationError;
import br.edu.ufcg.embedded.sam.models.Objective;
import br.edu.ufcg.embedded.sam.models.Project;
import br.edu.ufcg.embedded.sam.models.Question;
import br.edu.ufcg.embedded.sam.repositories.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for {@link br.edu.ufcg.embedded.sam.models.Objective}.
 */
@Service
public class ObjectiveService extends CrudService<Objective> {

    private ObjectiveRepository objectiveRepository;
    private QuestionService questionService;
    private ProjectService projectService;

    @Autowired
    public ObjectiveService(ObjectiveRepository objectiveRepository, ProjectService projectService, QuestionService questionService) {
        this.objectiveRepository = objectiveRepository;
        this.projectService = projectService;
        this.questionService = questionService;
    }

    /**
     * Creates an abstraction sheet linked to the {@link Project} that has {@code projectId} as its id.
     *
     * @param objective {@link Objective} to be created.
     * @param projectId id of the {@link Project} to which {@code abstractionSheet will be linked}.
     * @return The {@link Objective} that was created.
     */
    @Transactional
    public Objective create(Objective objective, Integer projectId) {
        Project project = projectService.find(projectId);
        attachQuestions(objective);
        if (project == null) {
            ValidationError error = new ValidationError();
            error.addError("project.not.found", "Project with id" + projectId + "not found.");
            throw error;
        }
        objective = objectiveRepository.save(objective);
        project.addObjective(objective);
        projectService.createOrUpdate(project);
        return objective;
    }

    /**
     * Attachs the questions of an objetive to the persistence context.
     * If theres a question that has not been created in the objetive, its created.
     * If a question is already created, it is retrieved from the database.
     *
     * @param objective
     */
    private void attachQuestions(Objective objective) {
        List<Question> attachedQuestions = new ArrayList<>();
        for (Question question : objective.getQuestions()) {
            if (question.getId() != null) {
                attachedQuestions.add(questionService.find(question.getId()));
            } else {
                attachedQuestions.add(questionService.create(question));
            }
        }
        objective.setQuestions(attachedQuestions);
    }

    public boolean remove(Integer idObjective, Integer idProject) {
        Objective objective = find(idObjective);
        Project project = projectService.find(idProject);
        if (objective != null && project != null) {
            project.removeObjective(objective);
            projectService.createOrUpdate(project);
            super.delete(objective);
            return true;
        }
        return false;
    }

    protected JpaRepository<Objective, Integer> getRepository() {
        return objectiveRepository;
    }
}

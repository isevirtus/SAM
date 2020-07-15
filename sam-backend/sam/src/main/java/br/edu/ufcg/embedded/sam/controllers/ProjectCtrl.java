package br.edu.ufcg.embedded.sam.controllers;

import br.edu.ufcg.embedded.sam.models.Project;
import br.edu.ufcg.embedded.sam.services.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectCtrl {

    /**
     * {@link ProjectService} for operations
     */
    private final ProjectService projectService;

    @Autowired
    public ProjectCtrl(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ApiOperation("create")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = projectService.createOrUpdate(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @ApiOperation("list")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Project>> list() {
        List<Project> projects = projectService.findAll();
        return new ResponseEntity<>(projects, HttpStatus.ACCEPTED);
    }

    @ApiOperation("update")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Project> update(@RequestBody final Project project) {
        Project newProject = new Project(
                project.getName(),
                project.getFunction(),
                project.getExperienceTime(),
                project.getDuration(),
                project.getAmountOfPeople(),
                project.getMethodologies(),
                project.getProjectType(),
                project.getObjectives());
        newProject.setId(project.getId());
        return new ResponseEntity<>(projectService.createOrUpdate(newProject), HttpStatus.ACCEPTED);
    }

    @ApiOperation("delete")
    @DeleteMapping(value = "/delete/{idProject}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> delete(@PathVariable("idProject") Integer idProject) {
        projectService.removeById(idProject);

        return new ResponseEntity<>("Projeto removido com sucesso", HttpStatus.ACCEPTED);
    }

    @ApiOperation("get-project")
    @GetMapping(value = "/get-project/{idProject}")
    public ResponseEntity<Project> getById(@PathVariable("idProject") Integer idProject) {
        Project project = projectService.find(idProject);
        return new ResponseEntity<Project>(project, HttpStatus.ACCEPTED);
    }
}

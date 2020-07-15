package br.edu.ufcg.embedded.sam.controllers;

import br.edu.ufcg.embedded.sam.models.Objective;
import br.edu.ufcg.embedded.sam.services.ObjectiveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/project/objective")
public class ObjectiveCtrl {

    /**
     * {@link ObjectiveService} for operations.
     */
    private final ObjectiveService objectiveService;

    @Autowired
    public ObjectiveCtrl(ObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @ApiOperation("create")
    @PostMapping(value = "/create/{idProject}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Objective> create(@RequestBody @Valid Objective objective,
                                            @PathVariable("idProject") Integer idProject) {
        objective = objectiveService.create(objective, idProject);
        return new ResponseEntity<>(objective, HttpStatus.CREATED);
    }

    @ApiOperation("update")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Objective> update(@RequestBody @Valid Objective objective) {
        objectiveService.createOrUpdate(objective);
        return new ResponseEntity<>(objective, HttpStatus.ACCEPTED);
    }

    @ApiOperation("delete")
    @DeleteMapping(value = "/delete/{idProject}/{idObjective}")
    public ResponseEntity<Void> delete(@PathVariable("idProject") Integer idProject,
                                       @PathVariable("idObjective") Integer idObjective) {
        if (objectiveService.remove(idObjective, idProject)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.notFound().build();
    }

    @ApiOperation("list")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Objective>> list() {
        List<Objective> sheets = objectiveService.findAll();
        return new ResponseEntity<>(sheets, HttpStatus.ACCEPTED);
    }

    @ApiOperation("get-objective")
    @GetMapping(value = "/get-objective/{idObjective}")
    public ResponseEntity<Objective> getById(@PathVariable("idObjective") Integer idObjective) {
        Objective objective = objectiveService.find(idObjective);
        return new ResponseEntity<Objective>(objective, HttpStatus.ACCEPTED);
    }
}
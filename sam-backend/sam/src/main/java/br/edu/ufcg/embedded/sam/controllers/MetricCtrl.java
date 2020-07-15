package br.edu.ufcg.embedded.sam.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufcg.embedded.sam.models.Metric;
import br.edu.ufcg.embedded.sam.services.MetricService;
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
@RequestMapping("/project/metric")
public class MetricCtrl {
    /**
     * {@link MetricService} for operations.
     */
    private final MetricService metricService;

    @Autowired
    public MetricCtrl(MetricService metricService) {
        this.metricService = metricService;
    }

    @ApiOperation("get-metric")
    @GetMapping(value = "/{idMetric}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Metric> getById(@PathVariable("idMetric") Integer idMetric){
        Metric metric = metricService.find(idMetric);
        if(metric != null) {
            return new ResponseEntity<>(metric, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiOperation("create")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Metric> create(@RequestBody @Valid Metric metric) {
        metric = metricService.createOrUpdate(metric);
        return new ResponseEntity<>(metric, HttpStatus.CREATED);
    }

    @ApiOperation("update")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Metric> update(@RequestBody @Valid Metric metric) {
        metricService.createOrUpdate(metric);
        return new ResponseEntity<>(metric, HttpStatus.ACCEPTED);
    }

    @ApiOperation("delete")
    @DeleteMapping(value = "/delete/{idMetric}")
    public ResponseEntity<String> delete(@PathVariable("idMetric") Integer idMetric) {
         metricService.removeById(idMetric);
        return new ResponseEntity<>("Metrica removida com sucesso", HttpStatus.ACCEPTED);
    }

    @ApiOperation("list")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Metric>> list() {
        List<Metric> sheets = metricService.findAll();
        return new ResponseEntity<>(sheets, HttpStatus.ACCEPTED);
    }
}

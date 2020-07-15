package br.edu.ufcg.embedded.sam.controllers;

import br.edu.ufcg.embedded.sam.models.bayesiannetwork.Network;
import br.edu.ufcg.embedded.sam.services.bayesiannetwork.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/network")
public class NetworkController {

    private NetworkService networkService;

    @Autowired
    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Network> update(@RequestBody Network network) {
        network = networkService.createOrUpdate(network);
        return new ResponseEntity<>(network, HttpStatus.OK);
    }

    @GetMapping(value = "/query/{idObjective}")
    public ResponseEntity<Network> query(@PathVariable("idObjective") Integer idObjective) {
        Network network = networkService.queryByObjective(idObjective);
        return new ResponseEntity<Network>(network, HttpStatus.OK);
    }
}

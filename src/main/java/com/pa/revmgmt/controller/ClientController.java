package com.pa.revmgmt.controller;

import com.pa.revmgmt.domain.Client;
import com.pa.revmgmt.domain.ClientType;
import com.pa.revmgmt.repo.AddressRepository;
import com.pa.revmgmt.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Controller that exposes client details
 */
@RestController
@RequestMapping("/clients")
public class ClientController {

    ClientRepository clientRepository;

    protected ClientController(){}

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    /**
     * End of point resource to create a new client
     *
     * @param newClient the client to be created
     */

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createNewClient(@RequestBody @Validated Client newClient){
        Client client = clientRepository.findByPhoneNumber(newClient.getPhoneNumber());

        if(client == null){
            int clientId = clientRepository.save(newClient).getId();
            System.out.println("=======cclient ID: null cncncn cncncnc ========" + clientId);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                    "/{id}").buildAndExpand(clientId).toUri();
            return ResponseEntity.created(location).build();
        }
        else{
            throw new RuntimeException("Client already exist " + client.toString());
        }
    }


    /**
     * End point resource to get a client info
     * @param clientId
     * @return client if found or exception otherwise
     */
    @GetMapping(path = "/{clientId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Client getClient(@PathVariable(value = "clientId") int clientId){
        return clientRepository.findById(clientId).orElseThrow(
                () -> new NoSuchElementException("ClientId=" + clientId + " does not exit"));
    }


    @GetMapping
    public List<Client> getClientsByType(@RequestParam("type") String clientType){
        return clientRepository.findByClientType(ClientType.valueOf(clientType));
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RuntimeException.class)
    public String return409(RuntimeException ex){
        return ex.getMessage();
    }

}

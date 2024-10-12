package com.cliente_persona.cliente_persona.controller;

import com.cliente_persona.cliente_persona.model.pojo.Customer;
import com.cliente_persona.cliente_persona.model.request.CreateCustomerRequest;
import com.cliente_persona.cliente_persona.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/clientes")
    public ResponseEntity<List<Customer>> getClients(@RequestHeader Map<String, String> headers) {
        log.info("headers: {}", headers);
        List<Customer> clients = customerService.getClients();
        return ResponseEntity.ok(Objects.requireNonNullElse(clients, Collections.emptyList()));
    }

    @GetMapping("/clientes/{clientId}")
    public ResponseEntity<Customer> getClient(@PathVariable String clientId) {

        log.info("Request received for product {}", clientId);
        Customer client = customerService.getClient(clientId);

        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/clientes/{clientesId}")
    public ResponseEntity<Void> deleteClient(@PathVariable String clientesId) {

        Boolean removed = customerService.removeClient(clientesId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/clientes")
    public ResponseEntity<Customer> createClient(@RequestBody CreateCustomerRequest request) {

        Customer createdClient = customerService.createClient(request);

        if (createdClient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/clientes/{clientesId}")
    public ResponseEntity<Customer> updateClient(@PathVariable String clientesId, @RequestBody CreateCustomerRequest clientRequest) {
        Customer client = customerService.updateClient(clientesId,clientRequest);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}



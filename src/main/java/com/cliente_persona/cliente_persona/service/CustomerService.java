package com.cliente_persona.cliente_persona.service;

import com.cliente_persona.cliente_persona.model.pojo.Customer;
import com.cliente_persona.cliente_persona.model.request.CreateCustomerRequest;

import java.util.List;

public interface CustomerService {

    List<Customer> getClients();

    Customer getClient(String clientId);

    Boolean removeClient(String clientId);

    Customer createClient(CreateCustomerRequest request);

    Customer updateClient(String clientId, CreateCustomerRequest clientRequest);

}

package com.cliente_persona.cliente_persona.service;

import com.cliente_persona.cliente_persona.data.CustomerRepository;
import com.cliente_persona.cliente_persona.data.PersonRepository;
import com.cliente_persona.cliente_persona.model.pojo.Customer;
import com.cliente_persona.cliente_persona.model.pojo.Person;
import com.cliente_persona.cliente_persona.model.request.CreateCustomerRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final PersonRepository personRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, PersonRepository personRepository) {
        this.customerRepository = customerRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Customer> getClients(){
        List<Customer> clientes = customerRepository.findAll();
        return clientes.isEmpty() ? null : clientes;
    }

    @Override
    public Customer getClient(String productId) {

        return customerRepository.findById(Long.valueOf(productId)).orElse(null);
    }

    @Override
    public Boolean removeClient(String productId) {

        Customer client = customerRepository.findById(Long.valueOf(productId)).orElse(null);

        if (client != null) {
            customerRepository.delete(client);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Customer createClient(CreateCustomerRequest request) {

        if (request != null && StringUtils.hasLength(request.contrasena().trim())
                && request.estado() != null  // Verifica que estado no sea nulo
                && request.personRequest() != null) {
            // Buscar la persona en el repositorio por su identificación.
            Optional<Person> optionalPerson = personRepository.findById(request.personRequest().identificacion());
            Person person;
            if (optionalPerson.isPresent()) {
                person = optionalPerson.get();
            } else {
                person = Person.builder()
                        .identificacion(request.personRequest().identificacion())
                        .nombre(request.personRequest().nombre())
                        .genero(request.personRequest().genero())
                        .edad(request.personRequest().edad())
                        .direccion(request.personRequest().direccion())
                        .telefono(request.personRequest().telefono())
                        .build();
                person = personRepository.save(person);
            }

            Customer client = Customer.builder()
                    .contrasena(request.contrasena())
                    .estado(request.estado())
                    .person(person)
                    .build();

            return customerRepository.save(client);
        } else {
            return null;
        }
    }

    @Override
    public Customer updateClient(String clientId, CreateCustomerRequest clientRequest) {
        Customer cliente = customerRepository.findById(Long.valueOf(clientId)).orElse(null);
        if (cliente !=null){
            Person oldPerson = cliente.getPerson();

            Person updatedPerson = oldPerson.toBuilder()
                    .identificacion(clientRequest.personRequest().identificacion())  // Usar el campo de PersonRequest
                    .nombre(clientRequest.personRequest().nombre())
                    .edad(clientRequest.personRequest().edad())
                    .genero(clientRequest.personRequest().genero())
                    .direccion(clientRequest.personRequest().direccion())
                    .telefono(clientRequest.personRequest().telefono())
                    .build();

            Customer updatedCustomer = cliente.toBuilder()
                    .contrasena(clientRequest.contrasena())
                    .estado(clientRequest.estado())
                    .person(updatedPerson)
                    .build();

            customerRepository.save(updatedCustomer);

            return updatedCustomer;
        }
        return null;
    }
}

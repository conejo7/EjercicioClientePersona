package com.cliente_persona.cliente_persona.service;

import com.cliente_persona.cliente_persona.data.CustomerRepository;
import com.cliente_persona.cliente_persona.data.PersonRepository;
import com.cliente_persona.cliente_persona.model.pojo.Customer;
import com.cliente_persona.cliente_persona.model.pojo.Person;
import com.cliente_persona.cliente_persona.model.request.CreateCustomerRequest;
import com.cliente_persona.cliente_persona.model.request.PersonRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    CustomerServiceImpl customerService;


    @Test
    void test_create_client_successful_creation() {

        PersonRequest personRequest = new PersonRequest("123456", "John Doe", "Male", 30, "123 Street", "555-1234");
        CreateCustomerRequest request = new CreateCustomerRequest("password123", true, personRequest);

        when(personRepository.findById("123456")).thenReturn(Optional.empty());
        when(personRepository.save(any(Person.class))).thenAnswer(returnsFirstArg());
        when(customerRepository.save(any(Customer.class))).thenAnswer(returnsFirstArg());

        Customer result = customerService.createClient(request);

        assertNotNull(result);
        assertEquals("password123", result.getContrasena());
        assertTrue(result.isEstado());
        assertEquals("123456", result.getPerson().getIdentificacion());

    }


    @Test
    void test_create_client_empty_password() {

        PersonRequest personRequest = new PersonRequest("123456", "John Doe", "Male", 30, "123 Street", "555-1234");
        CreateCustomerRequest request = new CreateCustomerRequest("   ", true, personRequest);

        Customer result = customerService.createClient(request);

        assertNull(result);

    }
}

package com.cliente_persona.cliente_persona.data;

import com.cliente_persona.cliente_persona.model.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

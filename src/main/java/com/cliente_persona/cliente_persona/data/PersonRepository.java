package com.cliente_persona.cliente_persona.data;

import com.cliente_persona.cliente_persona.model.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}

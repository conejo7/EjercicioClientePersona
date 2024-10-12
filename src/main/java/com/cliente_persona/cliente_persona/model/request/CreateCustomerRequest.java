package com.cliente_persona.cliente_persona.model.request;



public record CreateCustomerRequest(String contrasena, Boolean estado, PersonRequest personRequest) {
}

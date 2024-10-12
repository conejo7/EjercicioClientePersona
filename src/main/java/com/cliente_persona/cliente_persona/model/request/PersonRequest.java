package com.cliente_persona.cliente_persona.model.request;


public record PersonRequest(String identificacion, String nombre, String genero, int edad, String direccion, String telefono) {
}

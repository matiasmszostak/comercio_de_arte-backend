package com.arte.comercio.comerciobackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data // lombok
@NoArgsConstructor  // junto con Data, para no usar getters, setters y constructores
@Embeddable
public class Direccion {

    private String calle;
    private String numeroCalle;
    private String piso;
    private String departamento;
    private String codigoPostal;
    private String localidad;


}

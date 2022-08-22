package com.arte.comercio.comerciobackend.dto;

import com.arte.comercio.comerciobackend.models.Direccion;
import com.arte.comercio.comerciobackend.models.Obra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor //Constructor con todos los argunmentos
@Data //Getters y Setters
@NoArgsConstructor //Constructor vacío
public class ClienteDTO {

    private BigInteger id;

    private String apellido;
    private String nombres;
    private String celular;
    private String correoElectronico;
    private Direccion direccion;
    private List<Obra> obras;




}

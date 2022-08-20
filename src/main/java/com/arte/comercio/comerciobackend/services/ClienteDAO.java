package com.arte.comercio.comerciobackend.services;

import com.arte.comercio.comerciobackend.models.Cliente;

import java.util.Optional;

public interface ClienteDAO {

    Optional<Cliente> buscarClientePorMail(String mail);

    Optional<Cliente> buscarClientePorApellido(String apellido);


    Cliente altaNuevoCliente(Cliente cliente);


}

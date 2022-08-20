package com.arte.comercio.comerciobackend.repositories;

import com.arte.comercio.comerciobackend.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, BigInteger> {

    Optional<Cliente> findClienteByApellido(String apellido); // Optional permite que retorne null

    Optional<Cliente> findClienteByCorreoElectronico(String correoElectronico);

}

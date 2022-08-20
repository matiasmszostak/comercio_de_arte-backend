package com.arte.comercio.comerciobackend.repositories;


import com.arte.comercio.comerciobackend.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, BigInteger> {


    Optional<Artista> findArtistaByApellido(String apellido); // Optional permite que retorne null

    Optional<Artista> findArtistaByCorreoElectronico(String correoElectronico);


}

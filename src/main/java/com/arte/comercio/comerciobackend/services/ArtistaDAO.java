package com.arte.comercio.comerciobackend.services;

import com.arte.comercio.comerciobackend.models.Artista;

import java.util.Optional;

public interface ArtistaDAO {

    Optional<Artista> buscarArtistaPorMail(String mail);

    Optional<Artista> buscarArtistaPorApellido(String apellido);


    Artista altaNuevoArtista(Artista artista);



}

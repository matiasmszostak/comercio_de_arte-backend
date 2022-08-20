package com.arte.comercio.comerciobackend.services;


import com.arte.comercio.comerciobackend.models.Artista;
import com.arte.comercio.comerciobackend.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistaDAOImp implements ArtistaDAO{

    @Autowired // Inyección de dependencia ArtistaRepository en ArtistaDAO || Para no instanciar y que no entre en conflicto
    ArtistaRepository artistaRepository;




    @Override
    public Optional<Artista> buscarArtistaPorMail(String mail) {
        Optional<Artista> artista = artistaRepository.findArtistaByCorreoElectronico(mail); // implemento el método y mappeo "mail"
        return artista;
    }

    @Override
    public Optional<Artista> buscarArtistaPorApellido(String apellido) {
        Optional<Artista> artista = artistaRepository.findArtistaByApellido(apellido); // implemento el método del repositorio
        return artista;
    }

    @Override
    public Artista altaNuevoArtista(Artista artista) {
        if(this.artistaRepository
                .findArtistaByCorreoElectronico(artista.getCorreoElectronico().trim()) // busco al artista por email, trimmeo los espacios en blanco si hay
                .isPresent()){ // si está presente, no hago nada, para no duplicar al cliente
            throw new RuntimeException("El artista con email "+ artista.getCorreoElectronico() + ", ya existe"); // Excepción, error
        }

        return this.artistaRepository.save(artista); // guardo al cliente en la DB
    }





}

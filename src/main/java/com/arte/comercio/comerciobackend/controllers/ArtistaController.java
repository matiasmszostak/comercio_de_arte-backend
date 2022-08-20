package com.arte.comercio.comerciobackend.controllers;


import com.arte.comercio.comerciobackend.models.Artista;
import com.arte.comercio.comerciobackend.models.Cliente;
import com.arte.comercio.comerciobackend.repositories.ArtistaRepository;
import com.arte.comercio.comerciobackend.repositories.ClienteRepository;
import com.arte.comercio.comerciobackend.services.ArtistaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // anotación de controlador API
@RequestMapping("/artistas") //accedo a través de esta URL
public class ArtistaController {

    private final ArtistaDAO servicio;

    @Autowired
    private ArtistaRepository repositorio;


    public ArtistaController(ArtistaDAO servicio) {
        this.servicio = servicio;
    }

    //método post artista => alta artista
    @PostMapping()
    public ResponseEntity<?> altaArtista(@RequestBody Artista artista){ // @RequestBody => informo que los datos van a ir por el Body
        // <?> => no tipado, porque no lo toma bien el try catch
        //Validaciones de datos

        Artista artistaReturn = null; //inicializo variable en null

        //Try Catch, para atajar el error que definí en el servicio. Si el artista ya existe, devuelve el mensaje de error
        try{
            artistaReturn = this.servicio.altaNuevoArtista(artista); // si sale bien, se guarda en la variable

        }catch (RuntimeException e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
        //Si no hay error, devuelve el artista con un status de artista creado
        return new ResponseEntity<>(artistaReturn, HttpStatus.CREATED);
    }


    //Método get
    @GetMapping("/{mail}") //pongo el mail en la url
    public ResponseEntity<Artista> buscarArtistaPorMail(@PathVariable String mail){
        return ResponseEntity.ok(this.servicio
                .buscarArtistaPorMail(mail)
                .orElse(null)); // si no hay presencia de datos, responde null
    }


    @GetMapping()
    public List<Artista> listarTodosLosArtistas(){
        return repositorio.findAll();
    }







}

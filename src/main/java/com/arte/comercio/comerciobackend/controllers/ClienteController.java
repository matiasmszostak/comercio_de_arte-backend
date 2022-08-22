package com.arte.comercio.comerciobackend.controllers;


import com.arte.comercio.comerciobackend.dto.ClienteDTO;
import com.arte.comercio.comerciobackend.models.Cliente;
import com.arte.comercio.comerciobackend.repositories.ClienteRepository;
import com.arte.comercio.comerciobackend.services.ClienteDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // anotación de controlador API
@RequestMapping("/clientes") //accedo a través de esta URL
public class ClienteController {


    @Autowired
    private ClienteRepository repositorio;


    //utilizo la interface porque es más fácil de cambiar a futuro si es necesario
    //implemento el servicio
    private final ClienteDAO servicio;

    //inyecto la implementación del servicio
    public ClienteController(ClienteDAO servicio) {
        this.servicio = servicio;
    }



    //Metodo post con DTO y mappeo
    @PostMapping
    public ResponseEntity<ClienteDTO> altaCliente(@RequestBody ClienteDTO clienteDTO) throws Exception { // recibe ClienteDTO
        ObjectMapper om = new ObjectMapper(); // creo un ObjectMapper() (librería Jackson, sirve para mappear)
        Cliente cliente = om.convertValue(clienteDTO, Cliente.class); // mappeo un ClienteDTO a Cliente

        Cliente clienteEnDB = this.servicio.altaNuevoCliente(cliente); // Persisto al cliente con el servicio asociado

        ClienteDTO resultado = om.convertValue(clienteEnDB, ClienteDTO.class); // vuelvo a mappear, de cliente a ClienteDTO, que es lo que espera de salida
        return ResponseEntity.ok(resultado); // muestro el resultado con una respuesta ok


    }



    //método post cliente => alta cliente
/*
    @PostMapping()
    public ResponseEntity<?> altaCliente(@RequestBody Cliente cliente){ // @RequestBody => informo que los datos van a ir por el Body
        // <?> => no tipado, porque no lo toma bien el try catch
        //Validaciones de datos

        Cliente clienteReturn = null; //inicializo variable en null

        //Try Catch, para atajar el error que definí en el servicio. Si el cliente ya existe, devuelve el mensaje de error
        try{
           clienteReturn = this.servicio.altaNuevoCliente(cliente); // si sale bien, se guarda en la variable

        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
        //Si no hay error, devuelve el cliente con un status de cliente creado
        return new ResponseEntity<>(clienteReturn, HttpStatus.CREATED);
    }


*/

    //Método get
    @GetMapping("/{mail}") //pongo el mail en la url
    public ResponseEntity<Cliente> buscarClientePorMail(@PathVariable String mail){
        return ResponseEntity.ok(this.servicio
                .buscarClientePorMail(mail)
                .orElse(null)); // si no hay presencia de datos, responde null
    }

    @GetMapping()
    public List<Cliente> listarTodosLosClientes(){
        return repositorio.findAll();
    }




}

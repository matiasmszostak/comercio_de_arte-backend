package com.arte.comercio.comerciobackend.services;

import com.arte.comercio.comerciobackend.models.Cliente;
import com.arte.comercio.comerciobackend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Service que llama al ClienteRepository. Service => validaciones de negocio (la lógica)
public class ClienteDAOImp implements ClienteDAO{

    @Autowired // Inyección de dependencia ClienteRepository en ClienteDAO || Para no instanciar y que no entre en conflicto
    ClienteRepository clienteRepository;


    //Implemento los métodos de la interface ClienteDAO || Polimorfismo
    @Override
    public Optional<Cliente> buscarClientePorMail(String mail) {
        Optional<Cliente> cliente = clienteRepository.findClienteByCorreoElectronico(mail); // implemento el método y mappeo "mail"
        return cliente;
    }


    // ver si lo uso
    @Override
    public Optional<Cliente> buscarClientePorApellido(String apellido) {
        Optional<Cliente> cliente = clienteRepository.findClienteByApellido(apellido); // implemento el método del repositorio
        return cliente;
    }

    @Override
    public Cliente altaNuevoCliente(Cliente cliente) {
        if(this.clienteRepository
                .findClienteByCorreoElectronico(cliente.getCorreoElectronico().trim()) // busco al cliente por email, trimmeo los espacios en blanco si hay
                .isPresent()){ // si está presente, no hago nada, para no duplicar al cliente
            throw new RuntimeException("El cliente con email "+ cliente.getCorreoElectronico() + ", ya existe"); // Excepción, error
        }

        return this.clienteRepository.save(cliente); // guardo al cliente en la DB
    }
}

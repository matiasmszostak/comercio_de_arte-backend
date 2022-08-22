package com.arte.comercio.comerciobackend.services;

import com.arte.comercio.comerciobackend.models.Cliente;
import com.arte.comercio.comerciobackend.repositories.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Service que llama al ClienteRepository. Service => validaciones de negocio (la lógica)
public class ClienteDAOImp implements ClienteDAO{

    //El Logger sirve para hacer un seguimiento de errores. Usamos el de la librería slf4j
    private Logger logger = LoggerFactory.getLogger(ClienteDAOImp.class);

    public ClienteDAOImp() {
        logger.info("Se inicializa el Cliente DAO Imp");
    }

    @Autowired // Inyección de dependencia ClienteRepository en ClienteDAO || Para no instanciar y que no entre en conflicto
    private ClienteRepository clienteRepository;
    // Esta inyección de dependencias también se puede hacer poniendola en el constructor vacío (ClienteRepository repositorio) como parametro


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
    public Cliente altaNuevoCliente(Cliente cliente) throws Exception{
        if(this.clienteRepository
                .findClienteByCorreoElectronico(cliente.getCorreoElectronico().trim()) // busco al cliente por email, trimmeo los espacios en blanco si hay
                .isPresent()){ // si está presente, no hago nada, para no duplicar al cliente
            throw new Exception("El cliente con email "+ cliente.getCorreoElectronico() + ", ya existe"); // Excepción, error
        }

        return this.clienteRepository.save(cliente); // guardo al cliente en la DB
    }
}

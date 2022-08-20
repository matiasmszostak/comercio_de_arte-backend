package com.arte.comercio.comerciobackend.models;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


@Entity // Entidad
@Table(name= "clientes") // le cambio el nombre para que matchee con mi DB
@SequenceGenerator(name = "GN_cliente", sequenceName = "GN_cliente", allocationSize = 1) // armo una secuencia (un contador automático) para el id
public class Cliente {

    @Id // marco cual es el ID (PK)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GN_cliente") // de la secuencia, autogeneración de ID
    private BigInteger id;

    private String apellido;
    private String nombres;
    private String celular;
    @Column(name = "correo_electronico", unique = true)
    private String correoElectronico;

    @Embedded // uso la clase direccion
    @AttributeOverrides({ // le cambio el nombre de los atributos
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "numeroCalle", column = @Column(name = "numero_calle"))
    })
    private Direccion direccion;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // setteo la forma en la que trae los datos de la entidad || Cascade ALL trae todos los datos relacionados a la entidad y actualiza
    @JoinTable(name = "cliente_obra", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "obra_id")) // tabla de relación
    private List<Obra> obras;


//    constructor vacio
    public Cliente() {
    }

//    Java Bean: Objeto con un patrón determinado, que es el constructor por defecto con sus getters y setters.
//
    // todos menos el ID, que es autoincremental y lo define la DB automáticamente

    public Cliente(String apellido, String nombres, String celular, String correoElectronico, Direccion direccion) {
        this.apellido = apellido;
        this.nombres = nombres;
        this.celular = celular;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }


    //Getters & Setters


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Obra> getObras() {
        return obras;
    }

    public void setObras(List<Obra> obras) {
        this.obras = obras;
    }
}

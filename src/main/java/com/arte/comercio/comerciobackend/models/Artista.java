package com.arte.comercio.comerciobackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Data // lombok
@NoArgsConstructor  // junto con Data, para no usar getters, setters y constructores
@AllArgsConstructor
@Entity // Entidad
@Table(name= "artistas") // le cambio el nombre para que matchee con mi DB
@SequenceGenerator(name = "GN_artista", sequenceName = "GN_artista", allocationSize = 1) // armo una secuencia (un contador automático) para el id
public class Artista {

    @Id // marco cual es el ID (PK)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GN_artista") // de la secuencia, autogeneración de ID
    private BigInteger id;

    private String apellido;
    private String nombres;
    private String celular;
    @Column(name = "correo_electronico", unique = true)
    private String correoElectronico;
    private String pais;


    @Embedded // uso la clase direccion
    @AttributeOverrides({ // le cambio el nombre de los atributos
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "numeroCalle", column = @Column(name = "numero_calle"))
    })
    private Direccion direccion;




    //Relación de entidades: un artista puede tener varias obras, una obra pueden tener varios artistas
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // setteo la forma en la que trae los datos de la entidad || Cascade ALL trae todos los datos relacionados a la entidad y actualiza
    @JoinTable(name = "artista_obra", joinColumns = @JoinColumn(name = "artista_id"), inverseJoinColumns = @JoinColumn(name = "obra_id")) // tabla de relación
    private List<Obra> obras;

//
//    public Artista() {
//    }
//
//    public Artista(String apellido, String nombres, String celular, String correoElectronico, String pais, String tipoDeArte, Direccion direccion, List<Obra> obras) {
//        this.apellido = apellido;
//        this.nombres = nombres;
//        this.celular = celular;
//        this.correoElectronico = correoElectronico;
//        this.pais = pais;
//        this.direccion = direccion;
//        this.obras = obras;
//    }
//
//
//    public BigInteger getId() {
//        return id;
//    }
//
//    public void setId(BigInteger id) {
//        this.id = id;
//    }
//
//    public String getApellido() {
//        return apellido;
//    }
//
//    public void setApellido(String apellido) {
//        this.apellido = apellido;
//    }
//
//    public String getNombres() {
//        return nombres;
//    }
//
//    public void setNombres(String nombres) {
//        this.nombres = nombres;
//    }
//
//    public String getCelular() {
//        return celular;
//    }
//
//    public void setCelular(String celular) {
//        this.celular = celular;
//    }
//
//    public String getCorreoElectronico() {
//        return correoElectronico;
//    }
//
//    public void setCorreoElectronico(String correoElectronico) {
//        this.correoElectronico = correoElectronico;
//    }
//
//    public String getPais() {
//        return pais;
//    }
//
//    public void setPais(String pais) {
//        this.pais = pais;
//    }
//
//    public Direccion getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(Direccion direccion) {
//        this.direccion = direccion;
//    }
//
//    public List<Obra> getObras() {
//        return obras;
//    }
//
//    public void setObras(List<Obra> obras) {
//        this.obras = obras;
//    }
}

package com.arte.comercio.comerciobackend.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity // Entidad
@Table(name= "obras") // le cambio el nombre para que matchee con mi DB
@SequenceGenerator(name = "GN_obra", sequenceName = "GN_obra", allocationSize = 1)
@Data // lombok
@NoArgsConstructor  // junto con Data, para no usar getters, setters y constructores
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental
    private BigInteger id;
    private String nombre;
    private String anio;
    private String tipo;
    private String pais;
    private Integer precio;
    private Integer stock;
    @Column(name = "tipo_de_arte")
    private String tipoDeArte;


    //mappedBY => el dueño de la relación es Cliente, mappedBy al atributo de obras
    @ManyToMany(mappedBy = "obras", fetch = FetchType.LAZY) // relación de entidades many to many, armo lista de clientes || con fetch LAZY setteo que traiga los datos de la entidad bajo demanda
    private List<Cliente> clientes;




    //Relación de entidades: un artista puede tener varias obras, una obra pueden tener varios artistas
    @ManyToMany(mappedBy = "obras", fetch = FetchType.LAZY) // relación de entidades many to many, armo lista de clientes || con fetch LAZY setteo que traiga los datos de la entidad bajo demanda
    private List<Artista> artistas;





}

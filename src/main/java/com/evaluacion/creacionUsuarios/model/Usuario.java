package com.evaluacion.creacionUsuarios.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor

public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nombre;
    private String correo;
    private String contraseña;
    private LocalDateTime creado;
    private LocalDateTime modificado;
    private LocalDateTime ultimoIngreso;
    private String token;
    private boolean estaActivo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Telefono> telefonos;

    public Usuario()
    {
    }
}

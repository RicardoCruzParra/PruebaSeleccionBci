package com.evaluacion.creacionUsuarios.repository;

import com.evaluacion.creacionUsuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>
{
    Optional<Usuario> findByCorreo(String correo);
}

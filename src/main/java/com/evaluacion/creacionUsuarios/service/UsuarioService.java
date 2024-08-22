package com.evaluacion.creacionUsuarios.service;

import com.evaluacion.creacionUsuarios.exception.ResourceNotFoundException;
import com.evaluacion.creacionUsuarios.model.Usuario;
import com.evaluacion.creacionUsuarios.repository.UsuarioRepository;
import com.evaluacion.creacionUsuarios.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService
{
    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Create - Registrar un usuario
    public Usuario crearUsuario(Usuario usuario)
    {
        if (usuarioRepositorio.findByCorreo(usuario.getCorreo()).isPresent())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya registrado");
        }
        // Validación de correo y contraseña

        // Generar token JWT
        String token = jwtTokenProvider.crearToken(usuario.getCorreo());

        usuario.setToken(token);
        usuario.setCreado(LocalDateTime.now());
        usuario.setModificado(LocalDateTime.now());
        usuario.setUltimoIngreso(LocalDateTime.now());
        usuario.setEstaActivo(true);

        return usuarioRepositorio.save(usuario);
    }

    // Read - Obtener un usuario por ID
    public Usuario obtenerUsuarioPorId(UUID id)
    {
        return usuarioRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    // Read - Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    // Update - Actualizar un usuario
    public Usuario actualizarUsuario(UUID id, Usuario usuarioDetalles)
    {
        Usuario usuario = obtenerUsuarioPorId(id);

        usuario.setNombre(usuarioDetalles.getNombre());
        usuario.setCorreo(usuarioDetalles.getCorreo());
        usuario.setContraseña(usuarioDetalles.getContraseña());
        usuario.setModificado(LocalDateTime.now());
        // Actualizar otros campos según sea necesario

        return usuarioRepositorio.save(usuario);
    }

    // Delete - Eliminar un usuario
    public void eliminarUsuario(UUID id)
    {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuarioRepositorio.delete(usuario);
    }
}

package com.evaluacion.creacionUsuarios.controller;

import com.evaluacion.creacionUsuarios.model.Usuario;
import com.evaluacion.creacionUsuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/creacionUsuarios")
public class UsuarioController
{
    @Autowired
    private UsuarioService usuarioServicio;

    @PostMapping(value = "/registro", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario)
    {
        Usuario usuarioCreado = usuarioServicio.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }
}

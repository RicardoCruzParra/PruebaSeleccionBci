package com.evaluacion.creacionUsuarios.controller;

import com.evaluacion.creacionUsuarios.model.Usuario;
import com.evaluacion.creacionUsuarios.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
public class UsuarioControladorTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioServicio;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarUsuarioExitoso() throws Exception
    {
        // Datos de entrada
        Usuario usuario = new Usuario();
        usuario.setCorreo("juan@rodriguez.org");
        usuario.setNombre("Juan Rodriguez");
        usuario.setContraseña("hunter2");

        Usuario usuarioCreado = new Usuario();
        usuarioCreado.setId(UUID.randomUUID());
        usuarioCreado.setCorreo(usuario.getCorreo());
        usuarioCreado.setNombre(usuario.getNombre());
        usuarioCreado.setContraseña(usuario.getContraseña());
        usuarioCreado.setToken("mocked-token");
        usuarioCreado.setEstaActivo(true);

        // Mock
        when(usuarioServicio.crearUsuario(any(Usuario.class))).thenReturn(usuarioCreado);

        // Ejecución y verificación
        mockMvc.perform(post("/api/usuarios/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.correo").value("juan@rodriguez.org"))
                .andExpect(jsonPath("$.token").value("mocked-token"));
    }

    @Test
    public void testRegistrarUsuarioCorreoYaRegistrado() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setCorreo("juan@rodriguez.org");
        usuario.setNombre("Juan Rodriguez");
        usuario.setContraseña("hunter2");

        when(usuarioServicio.crearUsuario(any(Usuario.class)))
                .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya registrado"));

        mockMvc.perform(post("/api/usuarios/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El correo ya registrado"));
    }
}


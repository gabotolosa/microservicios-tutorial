package com.usuario.servicio.app.service;

import com.usuario.servicio.app.entity.Usuario;
import com.usuario.servicio.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //obtener todos los usuarios
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    //obtener usuario por el id
    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }
}

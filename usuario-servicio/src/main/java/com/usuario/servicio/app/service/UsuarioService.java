package com.usuario.servicio.app.service;

import com.usuario.servicio.app.entity.Usuario;
import com.usuario.servicio.app.modelo.Carro;
import com.usuario.servicio.app.modelo.Moto;
import com.usuario.servicio.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //obtener listado de los carro por id
    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate
                .getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate
                .getForObject("http://localhost:2003/moto/usuario/" + usuarioId, List.class);
        return motos;
    }

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

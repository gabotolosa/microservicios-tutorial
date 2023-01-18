package com.usuario.servicio.app.service;

import com.usuario.servicio.app.entity.Usuario;
import com.usuario.servicio.app.feignClients.CarroFeignClient;
import com.usuario.servicio.app.feignClients.MotoFeignClient;
import com.usuario.servicio.app.modelo.Carro;
import com.usuario.servicio.app.modelo.Moto;
import com.usuario.servicio.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

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

    //RestTemplate ->
    //obtener listado de los carro por id
    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate
                .getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate
                .getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
        return motos;
    }

    //guardar nuevo carro con feignclient
    public Carro saveCarro(int usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    //guardar nueva moto con feignclient
    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);
        return nuevaMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null){
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }
        resultado.put("Usuario", usuario);

        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        if (carros.isEmpty()){
            resultado.put("Carros", "El usuario no tiene carros");
        }
        else {
            resultado.put("Carros", carros);
        }

        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if (motos.isEmpty()){
            resultado.put("Motos", "El usuario no tiene motos");
        }
        else {
            resultado.put("Motos",motos);
        }
        return resultado;
    }
}

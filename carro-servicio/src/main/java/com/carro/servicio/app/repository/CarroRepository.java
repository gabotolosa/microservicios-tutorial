package com.carro.servicio.app.repository;

import com.carro.servicio.app.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

    //busca y lista carros por id del usuario
    List<Carro> findByUsuarioId(int usuarioId);
}

package com.usuario.servicio.app.feignClients;

import com.usuario.servicio.app.modelo.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8003")
@RequestMapping("/moto")
public interface MotoFeignClient {

    @PostMapping
    Moto save(@RequestBody Moto moto);

    @GetMapping("/usuario/{usuarioId}")
    List<Moto> getMotos(@PathVariable("usuarioId") int usuarioId);
}

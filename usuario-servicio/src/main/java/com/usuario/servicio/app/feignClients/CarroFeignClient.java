package com.usuario.servicio.app.feignClients;

import com.usuario.servicio.app.modelo.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "carro-service", url = "http://localhost:8002")
@RequestMapping("carro")
public interface CarroFeignClient {

    @PostMapping()
    Carro save(@RequestBody Carro carro);

}

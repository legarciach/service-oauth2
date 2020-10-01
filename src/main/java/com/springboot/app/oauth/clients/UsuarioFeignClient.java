package com.springboot.app.oauth.clients;

import com.springboot.app.commons.models.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-usuarios")
public interface UsuarioFeignClient {

    @GetMapping("/usuarios/search/buscar-username")
    public Usuario findByUsername(@RequestParam String username);

    @PutMapping("/usuarios/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);
}

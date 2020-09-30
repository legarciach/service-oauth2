package com.springboot.app.oauth.services;

import com.springboot.app.commons.models.entity.Usuario;
import com.springboot.app.oauth.clients.UsuarioFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioFeignClient clienteFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = clienteFeign.findByUsername(username);
        if(usuario ==null){
            log.error("Error en login. El usuario '"+username+"' No existe");
            throw new UsernameNotFoundException("Error en login. El usuario '"+username+"' No existe");
        }
        List<GrantedAuthority> autorities = usuario.getRoles().
                stream().
                map(role -> new SimpleGrantedAuthority(role.getNombre())).
                peek(autority -> log.info("Role: "+autority.getAuthority())).
                collect(Collectors.toList());
        log.info("Usuario '"+username+"' fue Autenticado");
        return new User(usuario.getUsername(),usuario.getPassword(), usuario.getEnabled(), true, true, true, autorities);
    }
}

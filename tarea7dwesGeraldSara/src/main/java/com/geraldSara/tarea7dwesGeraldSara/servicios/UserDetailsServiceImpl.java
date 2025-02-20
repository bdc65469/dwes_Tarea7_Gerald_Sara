package com.geraldSara.tarea7dwesGeraldSara.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.geraldSara.tarea7dwesGeraldSara.modelo.Credenciales;
import com.geraldSara.tarea7dwesGeraldSara.repositorios.CredencialesRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private CredencialesRepository credencialesRepository;

    public UserDetailsServiceImpl(CredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca el usuario en la BBDD
        Optional<Credenciales> credencialesOpt = credencialesRepository.findByUsuario(username);

        // Si no se encuentra, lanza una excepción
        if (!credencialesOpt.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Si se encuentra, retorna un objeto User de Spring Security
        Credenciales credenciales = credencialesOpt.get();
        return new User(
                credenciales.getUsuario(),
                credenciales.getPassword(),
                List.of(new SimpleGrantedAuthority(credenciales.getRol().name()))
        );
        
       
    }
}

package br.forsign.allo.usuario.service;

import br.forsign.allo.usuario.repository.UsuarioRepository;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */

@Service
public class UsuarioService implements UserDetailsService {

    @Resource
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}

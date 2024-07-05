package br.forsign.allo.config;

import br.forsign.allo.usuario.repository.UsuarioRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */

@Component
@CommonsLog
public class SecurityFilter extends OncePerRequestFilter {

    @Resource
    private UsuarioRepository usuarioRepository;

    @Resource
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.recoverToken(request);

        if(token != null){
            String login = tokenService.validateToken(token);
            UserDetails user = usuarioRepository.findByLogin(login);

            log.info(String.format("Usuario %s autenticado com acesso %s", user.getUsername(), user.getAuthorities()));

            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        return authHeader == null ? authHeader : authHeader.replace("Bearer ", "");
    }
}

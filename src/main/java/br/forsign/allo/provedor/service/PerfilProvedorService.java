package br.forsign.allo.provedor.service;

import br.forsign.allo.cliente.model.PerfilClienteOutput;
import br.forsign.allo.provedor.converter.PerfilProvedorMapper;
import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.model.PerfilProvedorOutput;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorCreator;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorGetter;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorUpdater;
import br.forsign.allo.usuario.domain.Usuario;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class PerfilProvedorService {

    @Resource
    private PerfilProvedorGetter getter;

    @Resource
    private PerfilProvedorCreator creator;

    @Resource
    private PerfilProvedorUpdater updater;

    @Resource
    private PerfilProvedorMapper mapper;

    public PerfilProvedorOutput getByProvedorId(Long idProvedor){
        log.info(String.format("Iniciando consulta de perfil com id provedor %s", idProvedor));

        return mapper.toOutput(getter.byProvedorId(idProvedor));
    }

    public PerfilProvedorOutput create(Provedor provedor, PerfilProvedorInput input){
        log.info(String.format("Iniciando cadastro de perfil para provedor %s", provedor.getRazaoSocial()));

        return mapper.toOutput(creator.create(provedor, input));
    }

    public PerfilProvedorOutput update(ProvedorInput input){
        log.info(String.format("Iniciando atualização de perfil para provedor %s", input.getRazaoSocial()));

        return mapper.toOutput(updater.update(input));
    }

    public PerfilProvedorOutput destacarAvaliacao(Long idAvaliacao) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        log.info(String.format("Iniciando destaque de avaliação de %s para provedor %s", idAvaliacao, usuario.getUsername()));

        return mapper.toOutput(updater.destacarAvaliacao(usuario.getLogin(), idAvaliacao));
    }
}



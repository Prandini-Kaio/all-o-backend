package br.forsign.allo.provedor.service.action.perfil;

import br.forsign.allo.common.utils.LocalDateUtils;
import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.repository.PerfilProvedorRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class PerfilProvedorCreator {

    @Resource
    private PerfilProvedorRepository repository;

    public PerfilProvedor create(Provedor provedor, PerfilProvedorInput input) {
        log.info(String.format("Cadastrando perfil de provedor %s.", provedor.getRazaoSocial()));

        PerfilProvedor perfilProvedor = new PerfilProvedor();

        perfilProvedor.setProvedor(provedor);
        perfilProvedor.setAvaliacao(null);
        perfilProvedor.setServicosConcluidos(0);
        perfilProvedor.setMediaAvaliacao(0);
        perfilProvedor.setTempoCadastro(LocalDateUtils.toBrazilianDateString(provedor.getDtRegistro()));
        perfilProvedor.setDescricao(input.getDescricao());

        return repository.save(perfilProvedor);

    }
}

package br.forsign.allo.provedor.service.action.perfil;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.repository.PerfilProvedorRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@CommonsLog
public class PerfilProvedorGetter {

    @Resource
    private PerfilProvedorRepository repository;

    public PerfilProvedor byProvedorId(Long idProvedor) {
        log.info(String.format("Consultando perfil de provedor %s.", idProvedor));

        return repository.findByIdProvedor(idProvedor).orElseThrow(CommonExceptionSupplier.naoEncontrado("PerfilProvedor", idProvedor));
    }

    public List<PerfilProvedor> listByProvedor(Long idProvedor) {
        return repository.findListByProvedor(idProvedor);
    }
}

package br.forsign.allo.provedor.service.action.perfil;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.repository.PerfilProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PerfilProvedorGetter {

    @Resource
    private PerfilProvedorRepository repository;

    public PerfilProvedor byProvedorId(Long idProvedor) {
        return repository.findByIdProvedor(idProvedor).orElseThrow(CommonExceptionSupplier.naoEncontrado("PerfilProvedor", idProvedor));
    }

    public List<PerfilProvedor> listByProvedor(Long idProvedor) {
        return repository.findListByProvedor(idProvedor);
    }
}

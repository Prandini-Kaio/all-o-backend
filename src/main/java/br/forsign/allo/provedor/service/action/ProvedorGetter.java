package br.forsign.allo.provedor.service.action;

import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProvedorGetter {

    @Resource
    private ProvedorRepository repository;

    public Page<Provedor> findAll(Pageable pageable) {
        return repository.findAtivos(pageable).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor"));
    }

    public Provedor byId(Long id){
        return repository
                .findById(id)
                .orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", id));
    }

    public List<Provedor> byFilter(String razaoSocial, Pageable pageable){
        return repository.findByFiltro(razaoSocial, pageable);
    }

    public Provedor byCpfCnpj(String cpfCnpj){
        return repository.findByCpfCnpj(cpfCnpj).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", cpfCnpj));
    }

    public boolean existsByCpfCnpj(String cpfCnpj){
        Optional<Provedor> provedor = repository.findByCpfCnpj(cpfCnpj);

        return provedor.isPresent();
    }

    public boolean existsById(Long id){
        Optional<Provedor> provedor = repository.findById(id);

        return provedor.isPresent();
    }
}

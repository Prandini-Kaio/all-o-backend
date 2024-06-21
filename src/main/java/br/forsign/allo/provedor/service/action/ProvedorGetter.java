package br.forsign.allo.provedor.service.action;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.profissao.converter.ProfissaoMapper;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Data
@Getter
@Setter

@Component
public class ProvedorGetter {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProfissaoMapper mapper;

    public Page<Provedor> findAll(Pageable pageable) {
        return repository.findAtivos(pageable).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor"));
    }

    public Provedor byId(Long id){
        return repository
                .findById(id)
                .orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", id));
    }

    public Page<Provedor> findByFilter(String razaoSocial, String profissao, Pageable pageable){
        return repository.byFilter(razaoSocial, profissao, pageable);
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

    public List<Provedor> byProfissao(Long idProfissao) {
        return repository.findByProfissoesId(idProfissao);
    }

    public int getTotalAvaliacao(List<Avaliacao> avaliacoes){
        return avaliacoes.size();
    }
}

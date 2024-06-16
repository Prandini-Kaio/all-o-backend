package br.forsign.allo.profissao.service.action;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.model.ProfissaoInput;
import br.forsign.allo.profissao.repository.ProfissaoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProfissaoCreator {

    @Resource
    private ProfissaoValidator validator;

    @Resource
    private ProfissaoRepository repository;

    public Profissao create(ProfissaoInput input) {
        validator.validarCreate(input);

        Profissao profissao = new Profissao();

        profissao.setNome(input.getNome());
        profissao.setSuggestion(false);
        profissao.setAtivo(true);

        return repository.save(profissao);
    }

    public Profissao createSugestion(String suggestion){

        validator.validaSuggestion(suggestion);

        Profissao profissao = new Profissao();

        profissao.setNome(suggestion);
        profissao.setSuggestion(true);

        return repository.save(profissao);
    }
}

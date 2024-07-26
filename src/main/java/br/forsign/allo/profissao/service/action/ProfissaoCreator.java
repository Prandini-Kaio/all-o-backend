package br.forsign.allo.profissao.service.action;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.model.ProfissaoInput;
import br.forsign.allo.profissao.repository.ProfissaoRepository;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class ProfissaoCreator {

    @Resource
    private ProfissaoValidator validator;

    @Resource
    private ProfissaoRepository repository;

    public Profissao create(ProfissaoInput input) {
        log.info(String.format("Criando uma nova profissão %s.", input.getNome()));

        validator.validarCreate(input);

        Profissao profissao = new Profissao();

        profissao.setNomeIcone(input.getNomeIcone());
        profissao.setNome(input.getNome().toLowerCase());
        profissao.setSuggestion(false);
        profissao.setAtivo(true);

        return repository.save(profissao);
    }

    public Profissao createSugestion(String suggestion){
        log.info(String.format("Criando uma nova sugestão %s.", suggestion));


        validator.validaSuggestion(suggestion);

        Profissao profissao = new Profissao();

        profissao.setNome(suggestion);
        profissao.setSuggestion(true);

        return repository.save(profissao);
    }
}

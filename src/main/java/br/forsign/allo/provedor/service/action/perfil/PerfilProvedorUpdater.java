package br.forsign.allo.provedor.service.action.perfil;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.service.action.AvaliacaoGetter;
import br.forsign.allo.provedor.converter.PerfilProvedorMapper;
import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.repository.PerfilProvedorRepository;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
@CommonsLog
public class PerfilProvedorUpdater {

    @Resource
    private PerfilProvedorRepository repository;

    @Resource
    private PerfilProvedorGetter getter;

    @Resource
    private ProvedorGetter provedorGetter;

    @Resource
    private PerfilProvedorMapper mapper;

    @Resource
    private AvaliacaoGetter avaliacaoGetter;

    public PerfilProvedor update(Provedor provedor){
        PerfilProvedor perfilProvedor = getter.byProvedorId(provedor.getId());

        PerfilProvedorInput input = mapper.toInput(perfilProvedor);

        input.setIdProvedor(provedor.getId());

        return this.update(input);
    }

    public PerfilProvedor update(PerfilProvedorInput input){

        log.info(String.format("Atualizando perfil do Provedor com id %s.", input.getIdProvedor()));

        Provedor provedor = provedorGetter.byId(input.getIdProvedor());
        Avaliacao avaliacao = avaliacaoGetter.byId(input.getIdAvaliacao());

        PerfilProvedor perfilProvedor = getter.byProvedorId(provedor.getId());

        perfilProvedor.setNome(provedor.getRazaoSocial());
        perfilProvedor.setEmail(provedor.getEmail());
        perfilProvedor.setDescricao(input.getDescricao());
        perfilProvedor.setPathToImage(input.getPerfilImage());

        perfilProvedor.setProvedor(provedor);
        perfilProvedor.setAvaliacao(avaliacao);

        perfilProvedor.setServicosConcluidos(0);
        perfilProvedor.setTempoCadastro(Period.between(provedor.getDtRegistro(), LocalDate.now()).getYears());

        List<Avaliacao> avaliacoesProvedor = avaliacaoGetter.byProvedor(input.getIdProvedor());
        perfilProvedor.setMediaAvaliacao(getMediaAvaliacao(avaliacoesProvedor));

        repository.save(perfilProvedor);

        return perfilProvedor;
    }

    private double getMediaAvaliacao(List<Avaliacao> avaliacoes){
        double mediaAvaliacoes = 0;

        if(!avaliacoes.isEmpty()){

            double notaTotal = 0;

            for(Avaliacao a : avaliacoes){
                notaTotal += a.getNota();
            }

            mediaAvaliacoes = (notaTotal / avaliacoes.size());
        }

        return mediaAvaliacoes;
    }
}

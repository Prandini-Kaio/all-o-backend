package br.forsign.allo.provedor.service.action.perfil;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.repository.AvaliacaoRepository;
import br.forsign.allo.avaliacao.service.action.AvaliacaoGetter;
import br.forsign.allo.common.utils.LocalDateUtils;
import br.forsign.allo.provedor.converter.PerfilProvedorMapper;
import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.PerfilProvedorRepository;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.servico.domain.Servico;
import br.forsign.allo.servico.service.action.ServiceGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
    private PerfilProvedorGetter perfilProvedorGetter;

    @Resource
    private PerfilProvedorMapper mapper;

    @Resource
    private AvaliacaoGetter avaliacaoGetter;

    @Resource
    private ServiceGetter serviceGetter;

    @Resource
    private AvaliacaoRepository avaliacaoRepository;

    public PerfilProvedor update(ProvedorInput input){

        log.info(String.format("Atualizando perfil do Provedor com id %s.", input.getId()));

        Provedor provedor = provedorGetter.byId(input.getId());

        PerfilProvedor perfilProvedor = getter.byProvedorId(provedor.getId());

        int totalServicos = serviceGetter.getTotalAvaliacoes(provedor.getId());

        perfilProvedor.setDescricao(input.getPerfilProvedorInput().getDescricao());
        perfilProvedor.setImagemPerfil(input.getPerfilProvedorInput().getPerfilImage());
        perfilProvedor.setTotalAvaliacao(totalServicos);

        perfilProvedor.setProvedor(provedor);

        perfilProvedor.setServicosConcluidos(0);
        perfilProvedor.setTempoCadastro(LocalDateUtils.toBrazilianDateString(provedor.getDtRegistro()));

        List<Avaliacao> avaliacoesProvedor = avaliacaoGetter.findAll();
        perfilProvedor.setMediaAvaliacao(getMediaAvaliacao(avaliacoesProvedor));

        return perfilProvedor;
    }

    public PerfilProvedor destacarAvaliacao(String username, Long idAvaliacao) {
        Provedor provedor = provedorGetter.byUsername(username);

        Avaliacao avaliacao = avaliacaoGetter.byId(idAvaliacao);

        PerfilProvedor perfilProvedor = perfilProvedorGetter.byProvedorId(provedor.getId());

        perfilProvedor.setProvedor(provedor);
        perfilProvedor.setAvaliacao(avaliacao);

        return repository.save(perfilProvedor);
    }

    public PerfilProvedor updateStats(Long idProvedor, Long idService){

        PerfilProvedor perfilProvedor = getter.byProvedorId(idProvedor);

        int totalAvaliacoes = serviceGetter.getTotalAvaliacoes(idProvedor);

        perfilProvedor.setMediaAvaliacao(getMediaAvaliacao(avaliacaoGetter.findAll()));
        perfilProvedor.setTotalAvaliacao(totalAvaliacoes);
        perfilProvedor.setServicosConcluidos(serviceGetter.getTotalAvaliacoes(idProvedor));

        return repository.save(perfilProvedor);
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

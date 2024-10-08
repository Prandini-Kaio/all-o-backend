package br.forsign.allo.provedor.service.action;

import br.forsign.allo.auth.service.AuthService;
import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.entidade.service.action.EnderecoCreator;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.service.action.ProfissaoGetter;
import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorCadastroInput;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import br.forsign.allo.provedor.service.PerfilProvedorService;
import br.forsign.allo.provedor.service.ProvedorValidator;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorCreator;
import br.forsign.allo.usuario.domain.Usuario;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@CommonsLog
public class ProvedorCreator {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProvedorValidator validator;

    @Resource
    private PerfilProvedorCreator perfilProvedorCreator;

    @Resource
    private ProfissaoGetter profissaoGetter;

    @Resource
    private EnderecoCreator enderecoCreator;

    @Resource
    private AuthService authService;

    public Provedor create(ProvedorCadastroInput provedorCadastroInput){

        log.info(String.format("Criando provedor com nome %s.", provedorCadastroInput.getProvedor().getRazaoSocial()));

        ProvedorInput input = provedorCadastroInput.getProvedor();

        validator.validarCreate(input);

        Provedor provedor = new Provedor();

        Profissao profissao = profissaoGetter.byIdAtivo(input.getIdProfissao());

        provedor.setRazaoSocial(input.getRazaoSocial());
        provedor.setEndereco(enderecoCreator.create(input.getEnderecoInput()));
        provedor.setTipoPessoa(input.getTipoPessoa());
        provedor.setEmail(input.getEmail());
        provedor.setTelefone(input.getTelefone());
        provedor.setCpfCnpj(CpfCnpjUtils.removeMascara(input.getCpfCnpj()));
        provedor.setProfissao(profissao);
        provedor.setAtivo(true);
        provedor.setDtRegistro(LocalDate.now());

        provedor.setUsuario((Usuario) this.authService.register(provedorCadastroInput.getUsuario()));

        PerfilProvedor perfil = this.perfilProvedorCreator.create(provedor, input.getPerfilProvedorInput());
        provedor.setPerfilProvedor(perfil);

        this.repository.save(provedor);

        return provedor;
    }
}

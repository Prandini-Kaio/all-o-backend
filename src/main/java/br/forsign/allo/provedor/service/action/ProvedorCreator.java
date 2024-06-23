package br.forsign.allo.provedor.service.action;

import br.forsign.allo.auth.service.AuthService;
import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.entidade.converter.EnderecoMapper;
import br.forsign.allo.entidade.service.action.EnderecoCreator;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.service.action.ProfissaoGetter;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.model.ProvedorCadastroInput;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import br.forsign.allo.provedor.service.PerfilProvedorService;
import br.forsign.allo.provedor.service.ProvedorValidator;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.domain.UsuarioRole;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ProvedorCreator {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProvedorValidator validator;

    @Resource
    private PerfilProvedorService perfilProvedorService;

    @Resource
    private ProfissaoGetter profissaoGetter;

    @Resource
    private EnderecoCreator enderecoCreator;

    @Resource
    private AuthService authService;

    private final Logger logger = LoggerFactory.getLogger(ProvedorCreator.class);

    public Provedor create(ProvedorCadastroInput provedorCadastroInput){

        logger.info("Criando provedor com nome {}.", provedorCadastroInput.getProvedor().getRazaoSocial());

        ProvedorInput input = provedorCadastroInput.getProvedor();

        validator.validarCreate(input);

        Provedor provedor = new Provedor();

        List<Profissao> profissao = input.getIdProfissoes().stream()
                        .map(profissaoGetter::byIdAtivo)
                        .toList();

        provedor.setRazaoSocial(input.getRazaoSocial());
        provedor.setEndereco(enderecoCreator.create(input.getEnderecoInput()));
        provedor.setTipoPessoa(input.getTipoPessoa());
        provedor.setEmail(input.getEmail());
        provedor.setTelefone(input.getTelefone());
        provedor.setCpfCnpj(CpfCnpjUtils.removeMascara(input.getCpfCnpj()));
        provedor.setProfissoes(profissao);
        provedor.setAtivo(true);
        provedor.setDtRegistro(LocalDate.now());

        provedor.setUsuario((Usuario) this.authService.register(provedorCadastroInput.getUsuario()));

        repository.save(provedor);
        perfilProvedorService.create(provedor, input.getPerfilProvedorInput());
        return provedor;
    }
}

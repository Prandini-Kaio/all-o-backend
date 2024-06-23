package br.forsign.allo.provedor.service;


import br.forsign.allo.common.error.BusinessException;
import br.forsign.allo.common.utils.CommonExceptionMessages;
import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.entidade.model.TipoPessoaEnum;
import br.forsign.allo.provedor.exceptions.ProvedorExceptionMessages;
import br.forsign.allo.provedor.model.ProvedorCadastroInput;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class ProvedorValidator {

    @Resource
    private ProvedorGetter getter;

    public void validarCreate(ProvedorInput input){
        validarDocumentos(input);
        validarDocumentoJaCadastrado(input);
    }

    private void validarDocumentos(ProvedorInput input) {
        validarTipoDocumento(input);
        validarDocumentoJaCadastrado(input);
    }

    public void validarUpdate(ProvedorInput input) {
        validarExistente(input.getId());
        validarDocumentos(input);
    }

    private void validarTipoDocumento(ProvedorInput input) {
        if(input.getTipoPessoa().equals(TipoPessoaEnum.FISICA) && !CpfCnpjUtils.isCpf(input.getCpfCnpj()))
            throw new BusinessException(ProvedorExceptionMessages.tipoDocumentoInvalido("Física", input.getCpfCnpj()));

        if(input.getTipoPessoa().equals(TipoPessoaEnum.JURIDICA) && !CpfCnpjUtils.isCpf(input.getCpfCnpj()))
            throw new BusinessException(ProvedorExceptionMessages.tipoDocumentoInvalido("Jurídica", input.getCpfCnpj()));
    }

    private void validarExistente(Long id){
        if(!getter.existsById(id))
            throw new BusinessException(CommonExceptionMessages.naoEncontrado("Provedor", id));
    }

    private void validarDocumentoJaCadastrado(ProvedorInput input){
        if(getter.existsByCpfCnpj(input.getCpfCnpj())){
            if(getter.byCpfCnpj(input.getCpfCnpj()).getId() != input.getId())
                throw new BusinessException(CommonExceptionMessages.entidadeJaCadastrada("Provedor", input.getCpfCnpj()));
        }
    }
}

package br.forsign.allo.provedor.service;


import br.forsign.allo.provedor.converter.ProvedorConverter;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.model.ProvedorOutput;
import br.forsign.allo.provedor.service.action.ProvedorCreator;
import br.forsign.allo.provedor.service.action.ProvedorDeleter;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.provedor.service.action.ProvedorUpdater;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class ProvedorService {

    @Resource
    private ProvedorCreator creator;

    @Resource
    private ProvedorGetter getter;

    @Resource
    private ProvedorUpdater updater;

    @Resource
    private ProvedorDeleter deleter;

    public Page<ProvedorOutput> byRazaoSocial(String razaoSocial, Pageable pageable) {
        log.info("Consultando provedor pela razão social no sistema.");

        return new PageImpl<>(getter.byFilter(razaoSocial, pageable)).map(ProvedorConverter::toOutput);
    }

    public ProvedorOutput create(ProvedorInput input) {
        log.info("Cadastrando provedor no sistema.");

        return ProvedorConverter.toOutput(creator.create(input));
    }

    public ProvedorOutput update(ProvedorInput input){
        log.info("Atualizando provedor no sistema.");

        return ProvedorConverter.toOutput(updater.update(input));
    }

    public void delete(Long id){
        log.info("Deletando provedor no sistema.");

        deleter.byId(id);
    }
}

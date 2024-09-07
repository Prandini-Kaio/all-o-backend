package br.forsign.allo.provedor.repository;

import br.forsign.allo.common.repositories.PagingRepository;
import br.forsign.allo.common.utils.QueryUtils;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorFilter;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */
public class ProvedorRepositoryCustomImpl implements ProvedorRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private PagingRepository pagingRepository;

    public Page<Provedor> byFilter(String nome, String profissao, Pageable pageable) {

        Map<String, Object> params = new HashMap<>();

        StringBuilder sbCount = new StringBuilder("SELECT COUNT(p) FROM Provedor p WHERE 1=1 ");
        StringBuilder sbQuery = new StringBuilder("SELECT p ");

        StringBuilder sbFrom = new StringBuilder();

        sbFrom.append("FROM Provedor p ")
                .append(" JOIN p.profissao profissao ");

        QueryUtils.safeAddParams(params, "nome", nome, sbFrom, " AND p.razaoSocial LIKE CONCAT('%',:nome,'%') ");
        QueryUtils.safeAddParams(params, "profissao", profissao, sbFrom, " AND profissao.nome LIKE CONCAT('%',:profissao,'%') ");

        return pagingRepository.getPage(pageable, params, sbQuery, sbCount);
    }

    @Override
    public List<Provedor> byFilter(ProvedorFilter filter) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sbQuery = new StringBuilder("SELECT p ");

        StringBuilder sbFrom = new StringBuilder();

        sbFrom.append("FROM Provedor p ")
                .append(" JOIN p.profissao profissao ")
                .append(" WHERE 1=1 ");

        QueryUtils.safeAddParams(params, "nome", filter.getRazaoSocial(), sbFrom, " AND p.razaoSocial LIKE CONCAT('%',:nome,'%') ");
        QueryUtils.safeAddParams(params, "profissao", filter.getProfissao(), sbFrom, " AND profissao.nome LIKE CONCAT('%',:profissao,'%') ");
        QueryUtils.safeAddParams(params, "ativo", filter.isAtivo(), sbFrom, " AND p.ativo = :ativo ");

        sbQuery.append(sbFrom);
        Query query = this.entityManager.createQuery(sbQuery.toString());
        params.forEach(query::setParameter);

        return query.getResultList();
    }

    @Override
    public List<Provedor> mostRelevant(ProvedorFilter filter) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sbQuery = new StringBuilder("SELECT p ");

        StringBuilder sbFrom = new StringBuilder();

        sbFrom.append("FROM Provedor p ")
                .append(" JOIN Servico s ON s.provedor.id = p.id ")
                .append(" WHERE 1=1 ");

        LocalDate now = LocalDate.now();
        LocalDate dataInicial = LocalDate.of(now.getYear(), now.getMonth(), 0);
        LocalDate dataFinal = LocalDate.of(now.getYear(), now.getMonth(), 0);

        sbFrom.append(" ");

        sbQuery.append(sbFrom);
        Query query = this.entityManager.createQuery(sbQuery.toString());
        params.forEach(query::setParameter);

        return query.getResultList();
    }

}

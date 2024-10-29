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
import java.time.LocalDateTime;
import java.time.YearMonth;
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

    @Override
    public List<Provedor> byFilter(ProvedorFilter filter) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sbQuery = new StringBuilder("SELECT p ");

        StringBuilder sbFrom = new StringBuilder();

        sbFrom.append("FROM Provedor p ")
                .append(" JOIN p.profissao profissao ")
                .append(" LEFT JOIN Servico s ON s.provedor.id = p.id AND s.dtRealizado >= :dataUltimoMes ")
                .append(" WHERE 1=1 ");

        QueryUtils.safeAddParams(params, "id", filter.getRazaoSocial(), sbFrom, " AND p.id = :id ");
        QueryUtils.safeAddParams(params, "nome", filter.getRazaoSocial(), sbFrom, " AND p.razaoSocial LIKE CONCAT('%',:nome,'%') ");
        QueryUtils.safeAddParams(params, "idProfissao", filter.getIdProfissao(), sbFrom, " AND profissao.id LIKE CONCAT('%',:idProfissao,'%') ");
        QueryUtils.safeAddParams(params, "ativo", filter.isAtivo(), sbFrom, " AND p.ativo = :ativo ");

        sbQuery.append(sbFrom);

        sbQuery.append(" GROUP BY p.id ");

        if(filter.isMaisRelevantes()){
            sbQuery.append(" ORDER BY COUNT(s.id) DESC ");
        }
        else if(filter.isMelhoresAvaliados()){
            sbQuery.append(" ORDER BY AVG(s.avaliacao.nota) DESC ");
        }
        else {
            sbQuery.append(" ORDER BY AVG(s.avaliacao.nota) DESC, COUNT(s.id) DESC ");
        }

        LocalDateTime dataUltimoMes = LocalDateTime.now().minusMonths(1);
        params.put("dataUltimoMes", dataUltimoMes);

        Query query = this.entityManager.createQuery(sbQuery.toString());
        params.forEach(query::setParameter);

        return query.getResultList();
    }

    @Override
    public List<Provedor> mostRelevant(Long idProfissao) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sbQuery = new StringBuilder("SELECT p ");

        StringBuilder sbFrom = new StringBuilder();

        sbFrom.append("FROM Provedor p ")
                .append(" JOIN Servico s ON s.provedor.id = p.id ")
                .append(" JOIN p.profissao pr ")
                .append(" WHERE 1=1 ");

        LocalDateTime now = LocalDateTime.now();

        YearMonth ym = YearMonth.from(now);

        LocalDateTime dataInicial = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0);
        LocalDateTime dataFinal = LocalDateTime.of(now.getYear(), now.plusMonths(1).getMonth(), ym.atEndOfMonth().getDayOfMonth(), 23, 59, 59);


        QueryUtils.safeAddParams(params, "dataInicial", dataInicial, sbFrom, " AND s.dtRealizado >= :dataInicial ");
        QueryUtils.safeAddParams(params, "dataFinal", dataFinal, sbFrom, " AND s.dtRealizado >= :dataFinal ");
        QueryUtils.safeAddParams(params, "idProfissao", idProfissao, sbFrom, " AND pr.id = :idProfissao ");

        sbFrom.append(" ORDER BY COUNT(s.id) DESC ");
        sbQuery.append(sbFrom);

        Query query = this.entityManager.createQuery(sbQuery.toString());
        params.forEach(query::setParameter);

        return query.getResultList();
    }

}

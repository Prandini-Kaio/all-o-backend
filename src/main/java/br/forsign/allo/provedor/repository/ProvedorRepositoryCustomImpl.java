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

        StringBuilder sbQuery = new StringBuilder("SELECT p, " +
                " COALESCE((SELECT COUNT(s.id) FROM Servico s WHERE s.provedor.id = p.id AND s.dtRealizado >= :dataUltimoMes), 0) AS totalServicos, " +
                " COALESCE((SELECT AVG(s.avaliacao.nota) FROM Servico s WHERE s.provedor.id = p.id AND s.dtRealizado >= :dataUltimoMes), 0) AS mediaNota, " +
                " COALESCE((SELECT AVG(s.avaliacao.agilidade) FROM Servico s WHERE s.provedor.id = p.id AND s.dtRealizado >= :dataUltimoMes), 0) AS mediaAgilidade, " +
                " COALESCE((SELECT AVG(s.avaliacao.preco) FROM Servico s WHERE s.provedor.id = p.id AND s.dtRealizado >= :dataUltimoMes), 0) AS mediaPreco, " +
                " ((COALESCE((SELECT AVG(s.avaliacao.nota) FROM Servico s WHERE s.provedor.id = p.id AND s.dtRealizado >= :dataUltimoMes), 0) + " +
                " COALESCE((SELECT AVG(s.avaliacao.agilidade) FROM Servico s WHERE s.provedor.id = p.id AND s.dtRealizado >= :dataUltimoMes), 0) + " +
                " COALESCE((SELECT AVG(s.avaliacao.preco) FROM Servico s WHERE s.provedor.id = p.id AND s.dtRealizado >= :dataUltimoMes), 0)) / 3) AS mediaGeral ");


        StringBuilder sbFrom = new StringBuilder();

        sbFrom.append("FROM Provedor p ")
                .append("JOIN p.profissao profissao ")
                .append("LEFT JOIN Servico s ON s.provedor.id = p.id ")
                .append("WHERE 1=1 ");

        QueryUtils.safeAddParams(params, "id", filter.getId(), sbFrom, " AND p.id = :id ");
        QueryUtils.safeAddParams(params, "nome", filter.getRazaoSocial(), sbFrom, " AND UPPER(p.razaoSocial) LIKE CONCAT('%', UPPER(:nome), '%') ");
        QueryUtils.safeAddParams(params, "idProfissao", filter.getIdProfissao(), sbFrom, " AND profissao.id = :idProfissao ");
        QueryUtils.safeAddParams(params, "ativo", filter.isAtivo(), sbFrom, " AND p.ativo = :ativo ");

        LocalDateTime dataUltimoMes = LocalDateTime.now()
                .minusMonths(1)
                .withDayOfMonth(1);
        params.put("dataUltimoMes", dataUltimoMes);

        sbQuery.append(sbFrom);
        sbQuery.append("GROUP BY p.id ");

        if (filter.isMaisRelevantes()) {
            sbQuery.append("ORDER BY totalServicos DESC ");
        } else if (filter.isMelhoresAvaliados()) {
            sbQuery.append("ORDER BY mediaGeral DESC ");
        } else {
            sbQuery.append("ORDER BY mediaGeral DESC, totalServicos DESC ");
        }

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
        LocalDateTime dataUltimoMes = LocalDateTime.now()
                .minusMonths(1)
                .withDayOfMonth(1);

        LocalDateTime dataInicial = dataUltimoMes.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dataFinal = now.withHour(23).withMinute(59).withSecond(59);

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

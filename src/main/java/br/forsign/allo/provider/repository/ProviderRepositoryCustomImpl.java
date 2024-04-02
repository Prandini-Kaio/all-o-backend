package br.forsign.allo.provider.repository;

/*
 * Created By Kaio Prandini
 * Date: 3/16/24
 * Time: 3:28 PM
 */

import br.forsign.allo.common.utils.QueryUtils;
import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.model.ProviderFilterDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProviderRepositoryCustomImpl implements ProviderCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Provider> getByFilter(ProviderFilterDTO filter, Pageable pageable) {

        StringBuilder sb = new StringBuilder();

        Map<String, Object> params = new HashMap<>();

        // Query
        sb.append("SELECT p FROM Provider p ")
                .append("JOIN p.profession pr ")
                .append("WHERE 1=1 ");

        // Setando os parametros da query
        buildParams(params, sb, filter);

        // Criando a query com base no StringBuilder
        Query query = this.entityManager.createQuery(sb.toString());

        params.forEach(query::setParameter);

        return query.getResultList();
    }

    private void buildParams(Map<String, Object> params, StringBuilder sb, ProviderFilterDTO filter){
        QueryUtils.safeAddParams(params, "id", filter.getId(), sb, " AND p.id = :id ");
        QueryUtils.safeAddParams(params, "name", filter.getName(), sb, " AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name,'%')) ");
        QueryUtils.safeAddParams(params, "desc",filter.getDescription(), sb, " AND p.description LIKE LOWER(CONCAT('%', :desc,'%')) ");
        QueryUtils.safeAddParams(params, "tipoPessoa", filter.getTipoPessoa(), sb, " AND p.tipoPessoa = :tipoPessoa ");
    }
}

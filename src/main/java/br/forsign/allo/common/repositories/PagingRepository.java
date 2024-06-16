package br.forsign.allo.common.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */

@Repository
public class PagingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public PageImpl getPage(Pageable paginacao, Map<String, Object> params, StringBuilder sbQuery, StringBuilder sbCount, StringBuilder sbFrom) {
        return this.getPage(paginacao, params, sbQuery.append(sbFrom), sbCount.append(sbFrom));
    }

    public PageImpl getPageOrderBy(Pageable paginacao, Map<String, Object> params, StringBuilder sbQuery, StringBuilder sbCount, StringBuilder sbFrom, String orderBy) {
        return this.getPage(paginacao, params, sbQuery.append(sbFrom).append(orderBy), sbCount.append(sbFrom));
    }

    public PageImpl getPage(Pageable paginacao, Map<String, Object> params, StringBuilder sbQuery, StringBuilder sbCount) {
        Query query = this.entityManager.createQuery(sbQuery.toString());
        params.forEach(query::setParameter);
        query.setMaxResults(paginacao.getPageSize())
                .setFirstResult((int) paginacao.getOffset());

        Query countQuery = this.entityManager.createQuery(sbCount.toString());
        params.forEach(countQuery::setParameter);

        long count;
        try{
            count = (long) countQuery.getSingleResult();
        } catch (NoResultException e){
            count = 0L;
        }
        return new PageImpl<>(query.getResultList(), paginacao, count);
    }
}

package dao;

import entity.Currency;
import datasource.MariaDbJpaConnection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class CurrencyDao {
    public void persist(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
    }

    public List<Currency> getCurrencies() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Currency> cq = cb.createQuery(Currency.class);
        cq.select(cq.from(Currency.class));
        return em.createQuery(cq).getResultList();
    }

    public double getExchangeRate(String abbreviation) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<Currency> root = cq.from(Currency.class);
        cq.select(root.get("rate"))
                .where(cb.equal(root.get("abbreviation"), abbreviation));
        return em.createQuery(cq).getSingleResult();
    }
    }


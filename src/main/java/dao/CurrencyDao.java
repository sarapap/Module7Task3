package dao;

import entity.Currency;
import datasource.MariaDbJpaConnection;
import jakarta.persistence.EntityManager;
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
        return em.createQuery("SELECT r FROM Currency r", Currency.class).getResultList();
    }

    public double getExchangeRate(String abbreviation) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        Currency currency = em.createQuery("SELECT r FROM Currency r WHERE r.abbreviation = :abbreviation", Currency.class)
                .setParameter("abbreviation", abbreviation)
                .getSingleResult();
        return currency.getRate();
    }
    }


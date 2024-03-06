package application;

import entity.*;
import dao.*;

public class CompanyAppController {
        public static void main(String[] args) {
            CurrencyDao currencyDao = new CurrencyDao();

            currencyDao.persist(new Currency("EUR", "Euro", 0.92));
            currencyDao.persist(new Currency("GBP", "British Pound", 0.78));

            Currency currency = currencyDao.find(1);
            System.out.println(currency.getAbbreviation() + " " + currency.getCurrencyName());

            currency.setCurrencyName("British Pound");
            currencyDao.update(currency);
        }
    }


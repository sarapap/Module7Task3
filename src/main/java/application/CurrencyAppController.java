package application;

import dao.CurrencyDao;
import entity.Currency;
import view.CurrencyConverterView;

import java.util.List;

public class CurrencyAppController {
    private CurrencyDao currencyDao;

    public CurrencyAppController() {
        currencyDao = new CurrencyDao();
    }

    public List<Currency> getCurrencies() {
        return currencyDao.getCurrencies();
    }

    public double convert(double amount, Currency fromCurrency, Currency toCurrency) {
        double fromRate = currencyDao.getExchangeRate(fromCurrency.getAbbreviation());
        double toRate = currencyDao.getExchangeRate(toCurrency.getAbbreviation());
        return amount * (toRate / fromRate);
    }

    public void addCurrency(Currency currency) {
        currencyDao.persist(currency);
    }

    public static void main(String[] args) {
        CurrencyConverterView.launch(CurrencyConverterView.class);
    }}


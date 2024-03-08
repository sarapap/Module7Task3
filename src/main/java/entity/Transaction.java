package entity;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    private int id;
    private double amount;
    private String fromCurrency;
    private String toCurrency;

    public Transaction(double amount, String fromCurrency, String toCurrency) {
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Transaction() {

    }

    public double getAmount() {
        return amount;
    }

}

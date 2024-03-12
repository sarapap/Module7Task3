package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="amount")
    private double amount;
    @Column(name="fromCurrency")
    private String fromCurrency;
    @Column(name="toCurrency")
    private String toCurrency;

    public Transaction(double amount, String fromCurrency, String toCurrency) {
        this.amount = amount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Transaction() {

    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

}

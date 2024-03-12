package entity;

import jakarta.persistence.*;
@Entity
@Table(name = "CurrencyObjects")
public class Currency {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="abbreviation")
    private String abbreviation;
    @Column(name="currency_name")
    private String currencyName;
    @Column(name="rate")
    private double rate;

    public Currency(String abbreviation, String currencyName, double rate) {
        super();
        this.abbreviation = abbreviation;
        this.currencyName = currencyName;
        this.rate = rate;
    }

    public Currency() {

    }

    public int getID() {
        return id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + currencyName;
    }

}

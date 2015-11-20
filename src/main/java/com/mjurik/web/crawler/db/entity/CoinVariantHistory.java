package com.mjurik.web.crawler.db.entity;

import com.mjurik.web.crawler.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

/**
 * Created by Marian Jurik on 20.9.2015.
 */
@Entity
public class CoinVariantHistory {

    @Id
    @GeneratedValue
    private String id;

    private LocalDate date;

    private BigDecimal price;
    private Currency currency;

    private String source;

    @ManyToOne
    private CoinVariant variant;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public CoinVariant getVariant() {
        return variant;
    }

    public void setVariant(CoinVariant variant) {
        this.variant = variant;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(Money price) {
        this.price = price.getAmount();
        this.currency = price.getCurrency();
    }
}

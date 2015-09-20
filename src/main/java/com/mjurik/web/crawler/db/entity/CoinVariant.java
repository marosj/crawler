package com.mjurik.web.crawler.db.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * Created by Marian Jurik on 20.9.2015.
 */
@Entity
public class CoinVariant {

    @Id
    @GeneratedValue
    private String id;

    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Variant variant;

    @ManyToOne
    private Coin coin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variant")
    @OrderBy(value = "date")
    private List<CoinVariantHistory> histories = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public List<CoinVariantHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<CoinVariantHistory> histories) {
        this.histories = histories;
    }

    public void addHistory(CoinVariantHistory history) {
        history.setVariant(this);
        // TODO MJUR order histories by date
        this.histories.add(history);
    }
}

package com.mjurik.web.crawler.db.entity;

import javax.persistence.Entity;

/**
 * Result of one euronumis.eu page
 *
 * Created by Marian Jurik on 28.6.2015.
 */
@Entity
public class EuronEuResult extends ResultEntity {

    private String name;

    private String ean;

    private String variant;

    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "EuronEuResult{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", ean='" + ean + '\'' +
                ", variant='" + variant + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

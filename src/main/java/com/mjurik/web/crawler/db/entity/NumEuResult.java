package com.mjurik.web.crawler.db.entity;


import javax.persistence.*;

/**
 * Result of one numizmatik.eu page
 *
 * Created by Marian Jurik on 27.6.2015.
 */
@Entity
public class NumEuResult extends ResultEntity {

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
        return "NumEuResult{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", ean='" + ean + '\'' +
                ", variant='" + variant + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

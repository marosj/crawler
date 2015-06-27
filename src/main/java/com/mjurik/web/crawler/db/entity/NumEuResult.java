package com.mjurik.web.crawler.db.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Result of one numizmatik.eu page
 *
 * Created by Marian Jurik on 27.6.2015.
 */
@Entity
public class NumEuResult {

    @Id
    @GeneratedValue
    private String id;

    private LocalDate startDate;

    private LocalDateTime startDateTime;

    private LocalDateTime processTime;

    private String name;

    private String path;

    private String ean;

    private String variant;

    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(LocalDateTime processTime) {
        this.processTime = processTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ean='" + ean + '\'' +
                ", variant='" + variant + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

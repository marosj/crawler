package com.mjurik.web.crawler.db.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marian Jurik on 20.9.2015.
 */
@Entity
public class Coin {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private NominalValue nominal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coin")
    @MapKey(name = "variant")
    private Map<Variant, CoinVariant> variants = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NominalValue getNominal() {
        return nominal;
    }

    public void setNominal(NominalValue nominal) {
        this.nominal = nominal;
    }

    public Map<Variant, CoinVariant> getVariants() {
        return variants;
    }

    public void setVariants(Map<Variant, CoinVariant> variants) {
        this.variants = variants;
    }

    public void addVariant(CoinVariant variant) {
        variant.setCoin(this);
        variants.put(variant.getVariant(), variant);
    }

    public boolean matchFulltextSearch(String searchString) {
        return id.toLowerCase().contains(searchString)
                || StringUtils.containsIgnoreCase(name, searchString)
                || StringUtils.containsIgnoreCase(nominal.toString(),searchString)
                || StringUtils.containsIgnoreCase(description, searchString);
    }
}

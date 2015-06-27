package com.mjurik.web.crawler.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marian Jurik on 23.6.2015.
 */
public class NumizmatikEuParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumizmatikEuParser.class);

    public static Result parse(String html) {
        Document doc = Jsoup.parse(html);
        Element identificationElem = doc.select("#product-identificator").first();
        if (identificationElem != null) {
            Result result = new Result();
            result.setId(identificationElem.text());

            Element nameElem = doc.select("h1").first();
            result.setName(nameElem.text());

            Element priceE = doc.select("#product-detail").first().select("meta[itemprop=price]").first();
            result.setPrice(priceE.attr("content"));

            Element qualityE = doc.select("#variant-57 option[selected=selected]").first();
            if (qualityE != null) {
                result.setSelectedVariant(qualityE.text());
            } else {
                result.setSelectedVariant("");
            }

            Elements qualitiesE = doc.select("#variant-57 option");
            if (qualitiesE.size() > 1) {
                for (Element element : qualitiesE) {
                    if (!"selected".equalsIgnoreCase(element.attr("selected"))) {
                        result.addVariant(element.attr("value"));
                    }
                }
            }

            return result;
        }
        return null;
    }

    public static class Result {
        private String id;
        private String name;
        private String price;
        private String selectedVariant;
        private List<String> variants = new ArrayList<>();

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSelectedVariant() {
            return selectedVariant;
        }

        public void setSelectedVariant(String selectedVariant) {
            this.selectedVariant = selectedVariant;
        }

        public void addVariant(String variant) {
            variants.add(variant);
        }

        public List<String> getVariants() {
            return variants;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "selectedVariant='" + selectedVariant + '\'' +
                    ", price='" + price + '\'' +
                    ", name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}

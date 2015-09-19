package com.mjurik.web.crawler.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by Marian Jurik on 27.6.2015.
 */
public class EuronumisEuParser {

    public static Result parse(String html) {
        Document doc = Jsoup.parse(html);
        Element itemElem = doc.select("div[itemtype=http://schema.org/Product").first();
        if (itemElem != null) {
            Element idnElem = itemElem.select("div[data-idn]").first();
            if (idnElem!= null) {
                Result result = new Result();
                result.setId(idnElem.attr("data-idn"));

                Element nameElem = itemElem.select("h1").first();
                result.setName(nameElem.text());

                Element priceE = itemElem.select("div[itemtype=http://schema.org/Offer]")
                        .first()
                        .select("input[name=nase_cena]").first();
                result.setPrice(priceE.attr("value"));

                Element qualityE = itemElem.select("input[name=Kvalita / Quality]").first();
                if (qualityE != null) {
                    result.setSelectedVariant(qualityE.attr("value"));
                } else {
                    result.setSelectedVariant("");
                }

                return result;
            }
        }
        return null;
    }

    public static class Result {
        private String id;
        private String name;
        private String price;
        private String selectedVariant;

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

        @Override
        public String toString() {
            return "Result{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", price='" + price + '\'' +
                    ", selectedVariant='" + selectedVariant + '\'' +
                    '}';
        }
    }

}

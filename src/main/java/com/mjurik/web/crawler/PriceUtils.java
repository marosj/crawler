package com.mjurik.web.crawler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Marian Jurik on 20.11.2015.
 */
public class PriceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceUtils.class);

    public static Money parse(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        String onlyNumbers = s.replaceAll("[^\\d,.]", "");
        if (StringUtils.isBlank(onlyNumbers)) {
            return null;
        }
        NumberFormat format;
        if (onlyNumbers.contains(",")) {
            format = NumberFormat.getInstance(new Locale("cs"));

        } else {
            format = NumberFormat.getInstance(Locale.US);
        }
        try {
            Number val = format.parse(onlyNumbers);
            BigDecimal amount = new BigDecimal(val.doubleValue());
            return Money.euros(amount);
        } catch (ParseException e) {
            LOGGER.error("Parse exception for {}: {}", s, e.getMessage());
            return null;
        }
    }
}

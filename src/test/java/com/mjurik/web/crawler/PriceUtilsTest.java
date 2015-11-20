package com.mjurik.web.crawler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marian Jurik on 20.11.2015.
 */
public class PriceUtilsTest {

    @Test
    public void testSimple() {
        Money price = PriceUtils.parse("123 EUR");
        assertNotNull(price);
        assertEquals(Money.euros(123), price);
    }

    @Test
    public void testSimple2() {
        Money price = PriceUtils.parse("123 €");
        assertNotNull(price);
        assertEquals(Money.euros(123), price);
    }

    @Test
    public void testSimple3() {
        Money price = PriceUtils.parse("€ 123");
        assertNotNull(price);
        assertEquals(Money.euros(123), price);
    }

    @Test
    public void test1() {
        Money price = PriceUtils.parse("€ 123,45");
        assertNotNull(price);
        assertEquals(Money.euros(123.45), price);
    }

    @Test
    public void test2() {
        Money price = PriceUtils.parse("€ 123.45");
        assertNotNull(price);
        assertEquals(Money.euros(123.45), price);
    }

    @Test
    public void testEmpty() {
        Money price = PriceUtils.parse("  ");
        assertNull(price);
    }

    @Test
    public void testEmpty2() {
        Money price = PriceUtils.parse("  EUR");
        assertNull(price);
    }

}
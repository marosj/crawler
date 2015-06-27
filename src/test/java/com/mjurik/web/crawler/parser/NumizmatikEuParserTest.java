package com.mjurik.web.crawler.parser;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Marian Jurik on 23.6.2015.
 */
public class NumizmatikEuParserTest {

    @Test
    public void testParse() throws Exception {
        URL url = Resources.getResource(NumizmatikEuParserTest.class, "NumizmatikEu1.html");
        String html = Resources.toString(url, Charsets.UTF_8);

        NumizmatikEuParser.Result result = NumizmatikEuParser.parse(html);
        assertNotNull(result);
        assertEquals("EAN: MIN1931", result.getId());
        assertEquals("10 EURO/2012 - Anton Bernolák – 250. výročie narodenia", result.getName());
        assertEquals("32 €", result.getPrice());
        assertEquals("PROOF", result.getSelectedVariant());
        assertThat(result.getVariants(), is(Arrays.asList("221")));
    }

    @Test
    public void testParse2() throws Exception {
        URL url = Resources.getResource(NumizmatikEuParserTest.class, "NumizmatikEu2.html");
        String html = Resources.toString(url, Charsets.UTF_8);

        NumizmatikEuParser.Result result = NumizmatikEuParser.parse(html);
        assertNotNull(result);
        assertEquals("EAN: MIN2352", result.getId());
        assertEquals("Matej Korvín (1458-1490), dukát bez letopočtu n-T", result.getName());
        assertEquals("1100 €", result.getPrice());
        assertEquals("", result.getSelectedVariant());
        assertThat(result.getVariants(), is(Collections.<String>emptyList()));
    }

    @Test
    public void testParse3() throws Exception {
        URL url = Resources.getResource(NumizmatikEuParserTest.class, "NumizmatikEu3.html");
        String html = Resources.toString(url, Charsets.UTF_8);

        NumizmatikEuParser.Result result = NumizmatikEuParser.parse(html);
        assertNotNull(result);
        assertEquals("EAN: MIN1834PL", result.getId());
        assertEquals("10 EURO/2012 - Chatam Sofer – 250. výročie narodenia", result.getName());
        assertEquals("185 €", result.getPrice());
        assertEquals("", result.getSelectedVariant());
        assertThat(result.getVariants(), is(Collections.<String>emptyList()));
    }

}
package com.mjurik.web.crawler.parser;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Marian Jurik on 23.6.2015.
 */
public class EuronumisEuParserTest {

    @Test
    public void testParse() throws Exception {
        URL url = Resources.getResource(EuronumisEuParserTest.class, "EuronumisEu1.html");
        String html = Resources.toString(url, Charsets.UTF_8);

        EuronumisEuParser.Result result = EuronumisEuParser.parse(html);
        assertNotNull(result);
        assertEquals("80473482", result.getId());
        assertEquals("10 EURO 2012 Slovensko BU Chatam Sofer", result.getName());
        assertEquals("119.9", result.getPrice());
        assertEquals("BU", result.getSelectedVariant());
    }

}
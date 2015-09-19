package com.mjurik.web.crawler;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mjurik.web.crawler.db.NumEuPersistence;
import com.mjurik.web.crawler.db.entity.NumEuResult;
import com.mjurik.web.crawler.parser.NumizmatikEuParser;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Created by alvin on 22.6.2015.
 */
public class NumizmatikEuCrawler extends WebCrawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumizmatikEuCrawler.class);

    private static final Pattern NOT_HTML_EXTENSIONS = Pattern.compile(
            ".*(\\.(css|js|bmp|gif|jpe?g"
                    + "|png|tiff?|mid|mp2|mp3|mp4"
                    + "|wav|avi|mov|mpeg|ram|m4v|pdf"
                    + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (NOT_HTML_EXTENSIONS.matcher(href).matches()) {
            return false;
        }

        return href.startsWith("http://www.numizmatik.eu/");
    }

    @Override
    public void visit(Page page) {
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String path = page.getWebURL().getPath();

        LOGGER.debug("Docid: {}", docid);
        LOGGER.debug("URL: {}", url);
        LOGGER.info("Path: '{}'", path);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();

            NumizmatikEuParser.Result parsedData = NumizmatikEuParser.parse(html);
            if (parsedData != null) {
                LOGGER.info("Parsed: {}", parsedData);
                persist(parsedData, path);

                if (parsedData.getVariants().size() > 0) {
                    for (String variant : parsedData.getVariants()) {
                        String newUrl = url + "?variant%5B57%5D=" + variant;
                        LOGGER.info("Adding url for new variant: {}", newUrl);
                        getMyController().addSeed(newUrl);
                    }
                }
            }
        }

        LOGGER.debug("=====");
    }

    private void persist(NumizmatikEuParser.Result result, String path) {
        NumEuResult entity = new NumEuResult();
        entity.setPath(path);
        entity.setProcessTime(LocalDateTime.now());
        entity.setStartDateTime(Utils.INST.getStartDateTime());
        entity.setStartDate(Utils.INST.getStartDate());
        entity.setEan(result.getId());
        entity.setName(result.getName());
        entity.setPrice(result.getPrice());
        entity.setVariant(result.getSelectedVariant());
        entity.setProcessed(false);

        NumEuPersistence.INSTANCE.persist(entity);
    }
}

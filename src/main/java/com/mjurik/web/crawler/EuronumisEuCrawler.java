package com.mjurik.web.crawler;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mjurik.web.crawler.db.EuronEuPersistence;
import com.mjurik.web.crawler.db.entity.EuronEuResult;
import com.mjurik.web.crawler.parser.EuronumisEuParser;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Created by alvin on 22.6.2015.
 */
public class EuronumisEuCrawler extends WebCrawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EuronumisEuCrawler.class);

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

        return href.startsWith("http://www.euronumis.eu/");
    }

    @Override
    public void visit(Page page) {
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String path = page.getWebURL().getPath();

        LOGGER.debug("Docid: {}", docid);
        LOGGER.debug("URL: {}", url);
        LOGGER.info("Path: '{}'", path);

        if (IgnoredPathList.EURONUMIS.isIgnored(path)) {
            LOGGER.info("Skipping parsing of path {} because it is in ignore list", path);
            return;
        }

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();

            EuronumisEuParser.Result parsedData = EuronumisEuParser.parse(html);
            if (parsedData != null) {
                LOGGER.info("Parsed: {}", parsedData);
                persist(parsedData, path);

            }
        }

        LOGGER.debug("=====");
    }

    private void persist(EuronumisEuParser.Result result, String path) {
        EuronEuResult entity = new EuronEuResult();
        entity.setPath(path);
        entity.setProcessTime(LocalDateTime.now());
        entity.setStartDateTime(Utils.INST.getStartDateTime());
        entity.setStartDate(Utils.INST.getStartDate());
        entity.setEan(result.getId());
        entity.setName(result.getName());
        entity.setPrice(result.getPrice());
        entity.setVariant(result.getSelectedVariant());
        entity.setProcessed(false);

        EuronEuPersistence.INSTANCE.persist(entity);
    }
}

package com.mjurik.web.crawler;

import com.mjurik.web.crawler.db.PersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


public class BasicCrawlController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicCrawlController.class);

    public static void main(String[] args) throws Exception {
    /*
     * crawlStorageFolder is a folder where intermediate crawl data is
     * stored.
     */
        String crawlStorageFolder = "target/crawled/";

        int numberOfCrawlers = 3;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setPolitenessDelay(1000);
        config.setMaxDepthOfCrawling(-1);
        config.setIncludeBinaryContentInCrawling(false);
        config.setResumableCrawling(false);

        config.setMaxPagesToFetch(30);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed("http://www.numizmatik.eu/c/mince/slovensko");

        LOGGER.info("Start date time {}", Utils.INST.getStartDateTime());
        controller.start(NumizmatikEuCrawler.class, numberOfCrawlers);

        LOGGER.info("Closing application");
        PersistenceManager.INSTANCE.close();
    }
}
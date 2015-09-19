package com.mjurik.web.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mjurik.web.crawler.db.PersistenceManager;
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
        LOGGER.info("Start date time {}", Utils.INST.getStartDateTime());

        LOGGER.info("Initializing ignore path lists");
        IgnoredPathList.EURONUMIS.init();
        IgnoredPathList.NUMIZMATIK.init();

        String crawlStorageFolder = "target/crawled/";

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

        CrawlController controllerNumizmatikEu = new CrawlController(config, pageFetcher, robotstxtServer);
        controllerNumizmatikEu.addSeed("http://www.numizmatik.eu/c/mince/slovensko");
        controllerNumizmatikEu.startNonBlocking(NumizmatikEuCrawler.class, 1);

        CrawlController controllerEuronumisEu = new CrawlController(config, pageFetcher, robotstxtServer);
        controllerEuronumisEu.addSeed("http://www.euronumis.eu/euronumis/eshop/6-1-Slovensko-Slovakia");
        controllerEuronumisEu.startNonBlocking(EuronumisEuCrawler.class, 1);

        controllerNumizmatikEu.waitUntilFinish();;
        controllerEuronumisEu.waitUntilFinish();;
        LOGGER.info("Closing application");
        PersistenceManager.INSTANCE.close();
    }
}
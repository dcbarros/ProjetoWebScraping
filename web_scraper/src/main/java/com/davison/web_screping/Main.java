package com.davison.web_screping;

import com.davison.web_screping.bot.Scraper;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scraper scraper = new Scraper();
        scraper.run();
    }
}
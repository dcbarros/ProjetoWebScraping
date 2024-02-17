package com.davison.web_screping;

import java.time.LocalDateTime;

import com.davison.web_screping.bot.Scraper;

public class Main {
    public static void main(String[] args) {

        Scraper scraper = new Scraper();
        scraper.run();
    }
}
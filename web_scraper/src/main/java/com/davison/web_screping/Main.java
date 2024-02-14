package com.davison.web_screping;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.davison.web_screping.bot.Scraper;

public class Main {
    public static void main(String[] args) {

        Scraper scraper = new Scraper();
        scraper.run();

    }
}
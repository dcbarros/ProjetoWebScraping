package com.davison.web_screping;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.brasilbandalarga.com.br/";
        try {
            WebDriver driver = new ChromeDriver();
            driver.get(url);
            try {
                WebElement cookiesButton = driver.findElement(By.xpath("//*[@id=\"card0\"]/div/div[2]/button[2]"));
                cookiesButton.click();  
                WebElement startButton = driver.findElement(By.xpath("//*[@id=\"btnIniciar\"]"));
                startButton.click();
                
                Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMinutes(1));
                wait.until(d -> startButton.isDisplayed());
                
                WebElement downloadInfo = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div[2]/div[1]/div/div[1]/div[3]/div/div/div/div/div[2]/div[1]/div/div[1]/div[2]"));
                WebElement uploadInfo = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div[2]/div[1]/div/div[1]/div[3]/div/div/div/div/div[2]/div[1]/div/div[2]/div[2]"));
                System.out.printf("Download: %s - Upload: %s",downloadInfo.getText(), uploadInfo.getText());
      
            } catch (org.openqa.selenium.NoSuchElementException e) {
                e.printStackTrace();
            }  
            finally {
                driver.quit();
            }

        } catch (Exception e) {
            
        }

    }
}
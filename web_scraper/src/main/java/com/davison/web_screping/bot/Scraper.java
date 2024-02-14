package com.davison.web_screping.bot;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scraper {

    private WebDriver driver;
    private String url;
    private String screenshotPath;
    private Integer waitingTime;

    
    public Scraper(){
        loadConfig();
    }

    private void loadConfig(){
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")){
            Properties properties = new Properties();

            if(input == null){
                System.out.println("O arquivo config.properties não foi encontrado");
                return;
            }

            properties.load(input);
            this.url = properties.getProperty("url");
            this.screenshotPath = properties.getProperty("screenshot_path");
            this.waitingTime = Integer.parseInt(properties.getProperty("wait_time"));

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            this.driver = new ChromeDriver(options);
            this.driver.get(url);

            try {
                WebElement cookiesButton = driver.findElement(By.xpath("//*[@id=\"card0\"]/div/div[2]/button[2]"));
                cookiesButton.click();  
                WebElement startButton = driver.findElement(By.xpath("//*[@id=\"btnIniciar\"]"));
                startButton.click();
                
                Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(this.waitingTime));
                wait.until(d -> startButton.isDisplayed());
                
                WebElement downloadInfo = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div[2]/div[1]/div/div[1]/div[3]/div/div/div/div/div[2]/div[1]/div/div[1]/div[2]"));
                WebElement uploadInfo = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div[2]/div[1]/div/div[1]/div[3]/div/div/div/div/div[2]/div[1]/div/div[2]/div[2]"));
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
                //System.out.println("Caminho do arquivo: " + java.nio.file.Paths.get(this.screenshotPath, "teste.png"));
                java.nio.file.Files.write(
                    java.nio.file.Paths.get(this.screenshotPath, "teste.png"),
                    screenshotBytes
                );
                System.out.printf("Download: %s - Upload: %s",downloadInfo.getText(), uploadInfo.getText());
      
            } catch (org.openqa.selenium.NoSuchElementException e) {
                e.printStackTrace();
            }  
            finally {
                driver.quit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

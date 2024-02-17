package com.davison.web_screping.bot;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
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

import com.davison.web_screping.utils.CsvWriter;

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
                String[] data = {downloadInfo.getText(), uploadInfo.getText(), LocalDateTime.now().toString() };
                takeSreenshot();
                saveData(data);
                      
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

    private void takeSreenshot() throws IOException{
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
        String saveFileStr = String.format("teste_automatizado_dia[%d_%d_%d]_hora(%d_%d_%d).png",
        LocalDateTime.now().getDayOfMonth(),
        LocalDateTime.now().getMonthValue(),
        LocalDateTime.now().getYear(),
        LocalDateTime.now().getHour(),
        LocalDateTime.now().getMinute(),
        LocalDateTime.now().getSecond()
        );
        
        java.nio.file.Files.write(
            java.nio.file.Paths.get(this.screenshotPath, saveFileStr),
            screenshotBytes
        );
    }

    private void saveData(String[] args) throws IOException{
        try {
            CsvWriter.writeToCsv(args);
        } catch (Exception e) {
        }
    }

}

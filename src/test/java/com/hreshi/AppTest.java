package com.hreshi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Test;

import java.util.List;

public class AppTest {
    @Test
    public void fetchMcq() {
        WebDriverManager.chromedriver().setup();

        String link= "https://codeforces.com/problemset";

        WebDriver driver = new ChromeDriver((new ChromeOptions()).setHeadless(true));
        driver.get(link);

        WebElement table = driver.findElement(By.className("problems"));
        List<WebElement> rows = driver.findElements(By.tagName("tr"));
        for (WebElement element : rows) {
            System.out.println (element.getText());
        }
        driver.quit();
    }
}
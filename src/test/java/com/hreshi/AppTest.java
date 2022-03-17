package com.hreshi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Test;

public class AppTest {
    @Test
    public void fetchMcq() {
        WebDriverManager.chromedriver().setup();


        System.out.println("Fetching Page has started ...");
        String link= "https://codeforces.com/problemset";
        WebDriver driver = new ChromeDriver();
        driver.get(link);
        
        driver.quit();
    }
}
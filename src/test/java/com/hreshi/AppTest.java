package com.hreshi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Test;
import org.junit.BeforeClass;

import java.util.List;
import java.util.ArrayList;

public class AppTest {
    WebDriver driver;

    public void setupSel () {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver((new ChromeOptions()).setHeadless(true));
        String link= "https://codeforces.com/problemset";
        driver.get(link);
    }

    @Test
    public void fetchProblems() {
        setupSel();
        List<WebElement> rows = driver.findElement(By.className("problems")).findElements(By.tagName("tr"));
        rows.remove(0);
        List<Problem> problemList = new ArrayList<>(rows.size());
        for (WebElement row : rows) {
            problemList.add(extractProblem(row.findElements(By.tagName("td"))));
        }

        for (Problem problem : problemList) {
            printProblem(problem);
        }
    }

    public void printProblem (Problem p) {
        System.out.println("###");
        System.out.println(p.code);
        System.out.println(p.name);
        System.out.println(p.link);
        System.out.println(p.rating);
        System.out.println(p.solvedBy);
        for (String str : p.tags) {
            System.out.print(str + ",");
        }
        System.out.println("###");
    }

    public Problem extractProblem (List<WebElement> td) {
        Problem problem = new Problem ();
        problem.setCode(extractCode(td.get(0)));
        problem.setLink(extractLink(td.get(0)));
        problem.setName(extractName(td.get(1)));
        problem.setTags(extractTags(td.get(1)));
        problem.setRating(extractRating(td.get(3)));
        problem.setSolvedBy(extractSolvedBy(td.get(4)));
        return problem;
    }
    private String extractCode(WebElement td) {
        return td.getText();
    }
    private String extractLink(WebElement td) {
        return td.findElement(By.tagName("a")).getAttribute("href");
    }
    private String extractName(WebElement td) {
        return td.findElement(By.tagName("a")).getText();
    }
    private List<String> extractTags(WebElement td) {
        List<String> tagList = new ArrayList<>();
        List<WebElement> elements = td.findElements(By.tagName("a"));
        for (int i = 1;i < elements.size();i++) {
            tagList.add(elements.get(i).getText());
        }
        return tagList;
    }
    private Integer extractRating(WebElement td) {
        String rating = td.getText();
        return new Integer(rating.equals("") ? -1 : Integer.parseInt(rating));
    }
    private String extractSolvedBy(WebElement td) {
        return td.getText();
    }
}
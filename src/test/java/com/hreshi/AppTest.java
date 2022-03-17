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
import java.io.File;

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
        driver.quit();
        File file = Excel.make(problemList);
        System.out.println("Excel Sheet is ready -> " + file.getPath());
    }

    // call this to print a problem
    public void printProblem (Problem p) {
        System.out.println("###");
        System.out.print(p.code + " ");
        System.out.println(p.name + " ");
        System.out.println(p.link + " ");
        System.out.println(p.rating + " ");
        System.out.println(p.solvedBy + " ");
        System.out.println(p.tags);
    }

    // extracts problem from a <tr> element
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

    // extracts problem code from first <td> element 
    private String extractCode(WebElement td) {
        return td.getText();
    }

    // extracts problem link from <a> of first <td> element
    private String extractLink(WebElement td) {
        return td.findElement(By.tagName("a")).getAttribute("href");
    }

    // extracts probelm name from <a> of second <td> element
    private String extractName(WebElement td) {
        return td.findElement(By.tagName("a")).getText();
    }

    // extracts tags from <a> elements of second <td> element except above name
    private String extractTags(WebElement td) {
        List<WebElement> elements = td.findElements(By.tagName("div"));
        WebElement tagElement = elements.get(1);
        return tagElement.getText();
    }

    // extracts rating from <td>
    private String extractRating(WebElement td) {
        return td.getText();
    }

    // extracts solvedBy from last td element
    // returns result like " x1011". Need to fix this
    private String extractSolvedBy(WebElement td) {
        return td.getText();
    }
}
package com.devops;

import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class resultportaltest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private void openPortal() {

        String pagePath = Paths
                .get("src/main/resources/result-portal/index.html")
                .toAbsolutePath()
                .toUri()
                .toString();

        driver.get(pagePath);
    }

    @Test
    public void verifyPageTitle() {

        openPortal();

        String title = driver.getTitle();

        Assert.assertEquals(
                title,
                "Student Result Portal"
        );
    }

    @Test
    public void verifyPageLoads() {

        openPortal();

        Assert.assertTrue(
                driver.findElement(By.id("rollNumber")).isDisplayed()
        );

        Assert.assertTrue(
                driver.findElement(By.id("checkResult")).isDisplayed()
        );
    }

    @Test
    public void verifyValidStudentResult() {

        openPortal();

        driver.findElement(By.id("rollNumber"))
                .sendKeys("CMR001");

        driver.findElement(By.id("checkResult"))
                .click();

        String resultText =
                driver.findElement(By.id("result")).getText();

        Assert.assertTrue(resultText.contains("Result Found"));
    }

    @Test
    public void verifyStudentName() {

        openPortal();

        driver.findElement(By.id("rollNumber"))
                .sendKeys("CMR001");

        driver.findElement(By.id("checkResult"))
                .click();

        String resultText =
                driver.findElement(By.id("result")).getText();

        Assert.assertTrue(resultText.contains("Madhav"));
    }

    @Test
    public void verifyMarks() {

        openPortal();

        driver.findElement(By.id("rollNumber"))
                .sendKeys("CMR001");

        driver.findElement(By.id("checkResult"))
                .click();

        String resultText =
                driver.findElement(By.id("result")).getText();

        Assert.assertTrue(resultText.contains("Java: 85"));
        Assert.assertTrue(resultText.contains("DBMS: 82"));
    }

    @Test
    public void verifyPercentage() {

        openPortal();

        driver.findElement(By.id("rollNumber"))
                .sendKeys("CMR001");

        driver.findElement(By.id("checkResult"))
                .click();

        String resultText =
                driver.findElement(By.id("result")).getText();

        Assert.assertTrue(
                resultText.contains("81.67%")
        );
    }

    @Test
    public void verifyPassResult() {

        openPortal();

        driver.findElement(By.id("rollNumber"))
                .sendKeys("CMR001");

        driver.findElement(By.id("checkResult"))
                .click();

        String resultText =
                driver.findElement(By.id("result")).getText();

        Assert.assertTrue(
                resultText.contains("PASS")
        );
    }

    @Test
    public void verifyInvalidRollNumber() {

        openPortal();

        driver.findElement(By.id("rollNumber"))
                .sendKeys("INVALID123");

        driver.findElement(By.id("checkResult"))
                .click();

        String resultText =
                driver.findElement(By.id("result")).getText();

        Assert.assertTrue(
                resultText.contains("Student not found")
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
package com.example.demo;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.driver;

class DemoApplicationTests {

    @Test
    public void demo() {
        String path = this.getClass().getResource("/Html_Root.html").getPath();
        open("file:///"+path);
        WebDriver driver = driver ().getWebDriver ();

        WebElement shadowHost = driver.findElement(By.cssSelector("div"));
        WebElement shadowRoot = getShadowRoot(driver,shadowHost);
        WebElement shadowTreeElement = shadowRoot.findElement(By.cssSelector("input"));
        shadowTreeElement.clear ();
        shadowTreeElement.sendKeys ( "123");
        Assertions.assertEquals ("123",shadowTreeElement.getAttribute ("value"));
    }

    private static WebElement getShadowRoot(WebDriver driver,WebElement shadowHost) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
    }
    public static WebElement getShadowElement(WebDriver driver,WebElement shadowHost, String cssOfShadowElement) {
        WebElement shardowRoot = getShadowRoot(driver, shadowHost);
        return shardowRoot.findElement(By.cssSelector(cssOfShadowElement));
    }

}

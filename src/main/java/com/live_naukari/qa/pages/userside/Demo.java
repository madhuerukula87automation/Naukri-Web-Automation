package com.live_naukari.qa.pages.userside;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
	WebDriver driver = new ChromeDriver();
	driver.get("https://news.ycombinator.com/");
	Thread.sleep(1000);
	driver.manage().window().maximize();

	driver.findElement(By.xpath("//a[text()='new']")).click();
	Thread.sleep(1000); // Wait for the new page to load

	// Find all the story rows
	List<WebElement> stories = driver.findElements(By.xpath("//tr[@class='athing submission']"));

	// For-each loop to print each story
	for (WebElement story : stories) {
	    String text = story.getText();
	    System.out.println(text);
	}

    }

}

package seleniumFramework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class TestSuite {
	
	public static void main(String[] args) throws IOException {
		
		//testOne();
		testTwo();
	}
	
	public static void testOne() throws IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\persi\\eclipse-workspace\\ProQuestSeleniumProject\\lib\\chromedriver_win32\\chromedriver.exe");
		final String url = "http://www.google.com";
		WebDriver driver = new ChromeDriver();
		WebElement element = null;
		driver.get(url);
		element = driver.findElement(By.className("gLFyf"));
		element.sendKeys("ProQuest");
		element.sendKeys(Keys.RETURN);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<WebElement> elements = driver.findElements(By.tagName("h3"));
		
		BufferedWriter out = null;

		try {
		    FileWriter fstream = new FileWriter("out.txt", true);
		    out = new BufferedWriter(fstream);
		    
			for(WebElement elmnt : elements) {
				
				String elmntText = elmnt.getText();
				System.out.println(elmntText);
				out.write(elmntText +"\n");
			}
    
		}

		catch (IOException e) {
		    System.err.println("Error: " + e.getMessage());
		}

		finally {
		    if(out != null) {
		        out.close();
		    }
		}
		driver.close();
	}
	
	public static void testTwo() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\persi\\eclipse-workspace\\ProQuestSeleniumProject\\lib\\chromedriver_win32\\chromedriver.exe");
		final String url = "https://www.proquest.com/";
		final String screenShotFile = "proquest_search_qa_result.png";
		WebDriver driver = new ChromeDriver();
		WebElement element = null;
		driver.get(url);
		element = driver.findElement(By.xpath("//*[@id=\"main-navbar-collapse\"]/ul[1]/li[8]/a/i"));
		element.click();
		
		element = driver.findElement(By.xpath("//*[@id=\"search-form\"]/div/span[1]/input[2]"));
		element.sendKeys("QA");
		element.sendKeys(Keys.RETURN);
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(screenshot, new File(screenShotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.close();
	}
}

package TestLogin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.edge.EdgeDriver;

public class GetCookies {
	private static EdgeDriver driver;
	private static String path_url= "https://hoidap.rocketacademy.asia/login/";
	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver", "D:\\QuaNG\\program\\edgedriver\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get(path_url);

		// Remove Recapcha
		JavascriptExecutor js = null;
		if (driver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) driver;
		}
		js.executeScript("return document.getElementsByClassName('g-recaptcha')[0].remove();");

		// Find Element Email, Passwword, Button Login
		driver.findElement(By.name("student_email")).sendKeys("quang.187pm20551@vanlanguni.vn");
		driver.findElement(By.name("student_password")).sendKeys("VLU187pm20551");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();

		// Create file contains Cookies Information
		File file = new File("Cookie_Login.data");
		try {
			// Delete old file if exists
			file.delete();
			file.createNewFile();
			FileWriter fileWrite = new FileWriter(file);
			BufferedWriter Bwrite = new BufferedWriter(fileWrite);
			// loop for getting the cookie information

			// loop for getting the cookie information
			for (org.openqa.selenium.Cookie ck : driver.manage().getCookies()) {
				Bwrite.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";"
						+ ck.getExpiry() + ";" + ck.isSecure()));
				Bwrite.newLine();
			}
			Bwrite.close();
			fileWrite.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

package TestLogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.edge.EdgeDriver;

public class ReadFileCookies {
	private static EdgeDriver driver;
	private static String path_url= "https://hoidap.rocketacademy.asia/login";
	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver", "D:\\QuaNG\\program\\edgedriver\\msedgedriver.exe");
		driver = new EdgeDriver();

		

		try {
			File file = new File("Cookie_Login.data");
			FileReader fileReader = new FileReader(file);
			try (BufferedReader Buffreader = new BufferedReader(fileReader)) {
				String strline;
				while ((strline = Buffreader.readLine()) != null) {
					StringTokenizer token = new StringTokenizer(strline, ";");
					while (token.hasMoreTokens()) {
						String name = token.nextToken();
						String value = token.nextToken();
						String domain = token.nextToken();
						String path = token.nextToken();

						Date expiry = null;

						String val;

						if (!(val = token.nextToken()).equals("null")) {
							expiry = new Date(val);		
						
						}						
						Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
						Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
						System.out.println(ck);
						driver.manage().addCookie(ck); // This will add the stored cookie to your current session
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		driver.get(path_url);
	}
}

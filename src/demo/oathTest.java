package demo;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class oathTest {

	public static void main(String[] args) throws InterruptedException
	{
		// Generating the code 
		/*String password = "saltlake@55";
		System.setProperty("webdriver.chrome.driver", "D://chromedriver_win32//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		/*driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.cssSelector("input[type='email]")).sendKeys("saurav.slk");
		driver.findElement(By.cssSelector("input[type='email]")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='email]")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type='email]")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);*/
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g4XojCixdqjg9xBP-akKeYw_RDaysYGq-6eUJVgWVPOCBRQpCOwdmuP5l-Q7TViVg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope=")[0];
		System.out.println("The code=" +code);
		
		
		//Generating the token using the code generated above
		String accessTokenResponse =  given().urlEncodingEnabled(false).
		queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessTokenResponse);
		
		String accesstoken = js.getString("access_token");
		
		System.out.println("access token ="+accesstoken);
		//Generating the response using the access token generated above
		String response = given().queryParam("access_token",accesstoken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println("The response=" +response);
		
	}

}

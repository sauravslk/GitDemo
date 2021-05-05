import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;


public class Basics {

	public static void main(String[] args)
	{
		
		//given -- all input details
		//when -- submit the api
		//then -- validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick23").header("Content-Type","application/json")
		.body(payload.Addplace()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeid = js.getString("place_id");
		
		System.out.println(placeid);
		
		// Update place
		
		String addr = "55 1st avenue";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				//"\"place_id\":\"10d8fb947f0ed0dd62d8b54709a02586\",\r\n" + 
				"\"address\":\""+addr+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200).
		    body("msg",equalTo("Address successfully updated"));
		
		
		//Get Place
		String getplaceresponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		//JsonPath js1 = new JsonPath(getplaceresponse);
		JsonPath js1 = ReUsableMethods.rawToJson(getplaceresponse);
		String actualAddress = js1.getString("address");
		
		System.out.println("Actual address =" +actualAddress);
		Assert.assertEquals(actualAddress, "Saurav");
				
	}
			
}

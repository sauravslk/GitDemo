import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJSONParse 

{
	
	public static void main(String args[])
	{
	JsonPath js = new JsonPath(payload.CoursePrice());
	
	//Print No of courses returned by API
	
	  int count =  js.getInt("courses.size()");
		System.out.println("The count of courses:" +count);
		
	// Print Purchase Amount
		
		int totalamount = js.getInt("dashboard.purchaseAmount");
		System.out.println("The total amount:" +totalamount);
		
		//Print Title of the first course
		
		String title1 = js.getString("courses[0].title");
		System.out.println("The name of 1st title :"+title1);
		
		// Print All course titles and their respective Prices
		System.out.println("The name of the titles and prices:");
		for (int i=0;i<count;i++)
		{
			System.out.println(js.getString("courses["+i+"].title"));
			System.out.println(js.getString("courses["+i+"].price").toString());
		}
		
		// Print no of copies sold by RPA Course
		
		for (int i=0;i<count;i++)
		{
			//System.out.println(js.getString("courses["+i+"].title"));
			String titlename = js.getString("courses["+i+"].title");
			if (titlename.equalsIgnoreCase("RPA"))
			{
				System.out.println("No.of copies:" +js.getString("courses["+i+"].copies"));
				break;
			}
		}
		
		
	}
	
}

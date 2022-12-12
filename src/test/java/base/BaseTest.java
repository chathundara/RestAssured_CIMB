package base;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.Constants;

public class BaseTest {

	protected static RequestSpecification requestSpect;
	protected static ResponseSpecification responseSpect;
	protected static String projectId;
	protected static String taskId;
	

	@BeforeClass
	public static void createSpecification() {
		requestSpect = new RequestSpecBuilder().setBaseUri(Constants.BASE_URI)
				.addHeader("Authorization", Constants.BEARER_TOKEN).addHeader("Content-Type", "application/json")
				.build();

		responseSpect = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
	}

}

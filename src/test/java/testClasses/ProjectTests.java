package testClasses;

import org.testng.annotations.Test;
import base.BaseTest;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Project;
import pojo.Task;

import static io.restassured.RestAssured.*;

public class ProjectTests extends BaseTest {

	@Test(priority = 1, description = "TC001: Create a New Project and verify the project created successfully")
	public void createProjectAndCheckResponseContent() {

		Project project = new Project();
		project.setName("DhanushkaTest4");
		project.setColor("charcoal");
		project.setFavorite(true);

		Response response = given().
				spec(requestSpect).
				body(project).when().
				post("/projects").
				then().
				spec(responseSpect).
				extract().
				response();

		String json = response.asString();
		System.out.println(json);

		JsonPath jp = new JsonPath(json);
		projectId = jp.get("id");

		Assert.assertEquals(jp.get("name"), "DhanushkaTest4");
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.contentType(), "application/json");
	}

	@Test(priority = 2, description = "TC002: Create a New Task under the project and verify it’s created successfully")
	public void createTasksAndCheckResponseContent() {
		Task task = new Task();
		task.setProjectID(projectId);
		task.setContent("Go to the Gym");
		task.setDescription("Today evening this should be happened");
		task.setPriority(2);
		

		Response response = given().
				spec(requestSpect).
				body(task).
				when().
				post("/tasks").
				then().
				spec(responseSpect).
				extract().
				response();

		String json = response.asString();
		System.out.println(json);
		JsonPath jp = new JsonPath(json);

		taskId = jp.get("id");
		Assert.assertEquals(jp.get("content"), "Go to the Gym");
		Assert.assertEquals(response.statusCode(), 200);
		

	}
	
	@Test(priority = 3, description = "TC003: Update the task content and verify")
	public void updateTasksAndCheckResponseContent() {

		Task task = new Task();
		task.setContent("Go to shopping");

		Response response = given().
				spec(requestSpect).
				body(task).
				when().
				post("/tasks/{taskId}", taskId).
				then().
				extract().
				response();

		String json = response.asString();
		JsonPath jp = new JsonPath(json);
		System.out.println(json);

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(jp.get("content"), "Go to shopping");
	}

	@Test(priority = 4, description = "TC004: Delete the task and verify")
	public void deleteTasksAndCheckResponseContent() {
		Response response = given().
				spec(requestSpect).
				when().
				delete("/tasks/{taskId}", taskId).
				then().
				extract().
				response();

		Assert.assertEquals(response.statusCode(), 204);
	}

}
package pojo;

import com.fasterxml.jackson.annotation.*;

@JsonPropertyOrder({ "project_id", "content", "description", "priority" })
public class Task {

	private String project_id;
	private String content;
	private String description;
	private Integer priority;

	public Task() {

	}

	public void setProjectID(String project_id) {
		this.project_id = project_id;
	}

	public String getProjectID() {
		return project_id;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}

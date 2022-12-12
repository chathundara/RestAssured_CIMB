package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

	public static String getScreenshotName(String methodName, String browserName) {
		String localDateTime = getCurrentDateTime();
		StringBuilder name = new StringBuilder().append(browserName).append("_").append(methodName).append("_")
				.append(localDateTime).append(".png");
		return name.toString();
	}

	/***
	 * Get a unique report name
	 * 
	 * @return
	 */
	public static String getReportName() {
		String localDateTime = getCurrentDateTime();
		StringBuilder name = new StringBuilder().append("Todoist RestAssured Report ").append(localDateTime)
				.append(".html");
		return name.toString();
	}

	public static String getCurrentDateTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String date = formatter.format(currentDate.getTime()).replace("/", "_");
		date = date.replace(":", "_");
		// .replace(" ", "_");

		return date;
	}
}

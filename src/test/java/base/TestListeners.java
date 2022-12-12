package base;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utils.ExtentManager;

public class TestListeners extends BaseTest implements ITestListener {
	private static ExtentReports extentReports = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	/**
	 * Invoked after the test class is instantiated and before any configuration
	 * method is called.
	 *
	 * @param context
	 */
	public void onStart(ITestContext context) {

	}

	/**
	 * Invoked after all the tests have run and all their Configuration methods have
	 * been called.
	 *
	 * @param context
	 */
	public void onFinish(ITestContext context) {

		extentReports.flush();
	}

	/**
	 * Invoked each time before a test method will be invoked.
	 *
	 * @param result
	 * @see ITestResult#STARTED
	 */
	public void onTestStart(ITestResult result) {

		ExtentTest test = extentReports.createTest(
				result.getInstanceName() + " : " + result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		test.info(Arrays.toString(result.getMethod().getGroups()));
		extentTest.set(test);
	}

	/**
	 * Invoked each time a test method succeeds.
	 *
	 * @param result
	 * @see ITestResult#SUCCESS
	 */
	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Method " + methodName + " Successful" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
	}

	/**
	 * Invoked each time a test method fails.
	 *
	 * @param result
	 * @see ITestResult#FAILURE
	 */
	public void onTestFailure(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=red>"
						+ "Exception Occurred: Click to see details: " + "</font>" + "</b>" + "</summary>"
						+ exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

		String logText = "<b>" + "Test Method " + methodName + " Failed" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

	}

	/**
	 * Invoked each time a test method is skipped.
	 *
	 * @param result
	 * @see ITestResult#SKIP
	 */
	public void onTestSkipped(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Method " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
	}

	/**
	 * Invoked each time a method fails but has been annotated with
	 * successPercentage and this failure still keeps it within the success
	 * percentage requested.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Ignore this
	}
}

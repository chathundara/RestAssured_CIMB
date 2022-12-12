package utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;




import java.io.File;

public class ExtentManager {
	

    private static ExtentReports extent;
 

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public static synchronized ExtentReports createInstance() {
        String fileName = Util.getReportName();
        String reportsDirectory = Constants.REPORTS_DIRECTORY;
        new File(reportsDirectory).mkdirs();
        String path = reportsDirectory + fileName;

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("API Automation Run");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
       
        htmlReporter.config().setAutoCreateRelativePathMedia(true);

        extent = new ExtentReports();
        extent.setSystemInfo("Organization", "CIMB");
        extent.setSystemInfo("Author", "Dhanushka Chathundara");
      
        extent.attachReporter(htmlReporter);

        return extent;
    }
    


}


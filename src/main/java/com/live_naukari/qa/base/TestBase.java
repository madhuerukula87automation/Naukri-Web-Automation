package com.live_naukari.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.live_naukari.qa.pages.userside.Login_Page;
import com.live_naukari.qa.util.Chrome_Prefs;
import com.live_naukari.qa.util.Data_Utility;
import com.live_naukari.qa.util.Delete_Files_In_Directory;
import com.live_naukari.qa.util.Helper_s;
import com.live_naukari.qa.util.ReadConfig;
import com.live_naukari.qa.util.Screenshot;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public static Properties prop;
    public static ReadConfig readconfig = new ReadConfig();
    public static Login_Page loginPage;
    static Helper_s helpers;
    public Chrome_Prefs c = new Chrome_Prefs();
    public Data_Utility DU = new Data_Utility();
    public ExtentTest extentTest;
    ExtentReports extent = new ExtentReports();

    /*
     * Deleting Screenshots from previous run
     */

    private static String TargetFile = System.getProperty("user.dir") + "/screenshots/";
    private static String TargetReports = System.getProperty("user.dir") + "/test-output/";
    private static String TargetSurefire = System.getProperty("user.dir") + "/target/surefire-reports/";
    Delete_Files_In_Directory df = new Delete_Files_In_Directory();

    @BeforeSuite
    public void beforeSuite() {
	df.deleteFilesInScreenshots(TargetFile);
	df.deleteFilesInScreenshots(TargetReports);
	df.deleteFilesInScreenshots(TargetSurefire);

    }

    public TestBase() {
	try {
	    prop = new Properties();
	    FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/live_naukari"
		    + "/qa/config/config.properties");
	    prop.load(ip); // EmpCloud/src/main/java/com/cloud/qa/config/config.properties
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Parameters("browser")
    @BeforeMethod
    public void initialization(@Optional("") String browserName) throws InterruptedException {

	if (browserName == null || browserName.isEmpty()) {
	    browserName = prop.getProperty("Browser");
	}

	System.out.println("Browser selected: " + browserName);

	HashMap<String, Object> chromePrefs = new HashMap<>();
	chromePrefs.put("profile.default_content_settings.popups", 0);
	chromePrefs.put("download.default_directory", System.getProperty("user.dir") + "\\screenshots\\");
	ChromeOptions options = new ChromeOptions();
	options.setExperimentalOption("prefs", chromePrefs);

	if (browserName.equalsIgnoreCase("chrome")) {
	    System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
	    System.setProperty("webdriver.chrome.silentOutput", "true");
	    WebDriverManager.chromedriver().setup();
	    driver.set(new ChromeDriver(options));
	    Dimension dimension = new Dimension(450, 600);
	    getDriver().manage().window().setSize(dimension);
	    options.setCapability("timeouts", "{implicit: 30000, pageLoad: 300000, script: 90000}");
	    System.out.println("TIMEOUTS FOR CHROME ARE :" + options.getCapability("timeouts"));
	    DesiredCapabilities chrome = new DesiredCapabilities();
	    chrome.setCapability("timeouts", "{implicit: 30000, pageLoad: 300000, script: 90000}");
	    chrome.merge(options);
	    System.out.println("Desired Capabilities are: " + chrome.getCapability("timeouts"));

	} else if (browserName.equalsIgnoreCase("firefox")) {
	    System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxpath());
	    WebDriverManager.firefoxdriver().setup();
	    driver.set(new FirefoxDriver());

	} else if (browserName.equalsIgnoreCase("opera")) {

	    WebDriverManager.chromedriver().driverVersion("131.0.6778.266").setup(); // Setup ChromeDriver for Opera

	    ChromeOptions options1 = new ChromeOptions();
	    options1.setBinary("C:\\Users\\MADHU E\\AppData\\Local\\Programs\\Opera\\opera.exe"); // Set Opera binary //
												  // path
	    options1.addArguments("--start-maximized"); // Ensure full window opens
	    driver.set(new ChromeDriver(options1)); // Use ChromeDriver instead of OperaDriver
	    System.out.println("Opera Browser is launched.");

	} else if (browserName.equalsIgnoreCase("brave")) {
	    System.setProperty("webdriver.chrome.driver", readconfig.getChromepath()); // Use ChromeDriver
	    WebDriverManager.chromedriver().setup();

	    ChromeOptions options1 = new ChromeOptions();
	    options1.setBinary(
		    "C:\\Users\\MADHU E\\AppData\\Local\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
	    driver.set(new ChromeDriver(options1));
	    System.out.println("Brave Browser is launched.");
	} else if (browserName.equalsIgnoreCase("edge")) {
	    System.setProperty("webdriver.edge.driver", readconfig.getEdgePath());
	    WebDriverManager.edgedriver().setup();
	    System.setProperty("webdriver.edge.driver", readconfig.getEdgePath());
	    WebDriverManager.edgedriver().setup();

	    // Configure EdgeOptions
	    EdgeOptions options1 = new EdgeOptions();
	    options1.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation")); // Suppress
												   // automation message
	    options1.setExperimentalOption("useAutomationExtension", false); // Disable automation extension
	    options1.addArguments("--disable-blink-features=AutomationControlled");
	    // Disable the "Save Password" popup
	    Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false); // Disable credential saving
	    prefs.put("profile.password_manager_enabled", false); // Disable password manager
	    options1.setExperimentalOption("prefs", prefs); // ✅ Apply preferences to options1

	    // Initialize the driver with options
	    driver.set(new EdgeDriver(options1)); // ✅ Use options1 correctly

	} else {
	    Assert.assertTrue(true, "No Browser type sent or Browser not Mention in this Method");
	}

	System.out.println("Browser setup by Thread " + Thread.currentThread().getId() + " and Driver reference is : "
		+ getDriver());
	getDriver().manage().window().maximize();
	getDriver().manage().deleteAllCookies();
	/*
	 * URL
	 */
	getDriver().get(readconfig.getApplicationURL("Dev"));

	/*
	 * Login -
	 */
	loginPage = new Login_Page(getDriver());
	loginPage.login(readconfig.getUserName(), readconfig.getPassword());

	/*
	 * Admin Side
	 */

//		getDriver().get(readconfig.Non_ApplicationURL("manager_url"));
//		
//		loginPage = new LoginPage(getDriver());
//		loginPage.login(readconfig.Non_getUserName(), readconfig.Non_getPassword());

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

	if (result.getStatus() == ITestResult.FAILURE) {
	    // extentTest.log(Status.FAIL, (Markup)
	    // extentTest.addScreenCaptureFromPath(TargetFile));
	    Screenshot.captureScreenshot(result.getName());
	    takeScreenShot(result);
	}
//		 //extent.flush();
	closeBrowser();

    }

    public static WebDriver getDriver() {
	return driver.get();
    }

    public static void closeBrowser() {
	driver.get().quit();
	driver.remove();
    }

    private void takeScreenShot(ITestResult result) {

    }

}

package com.live_naukari.qa.pages.userside;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import com.live_naukari.qa.base.TestBase;
import com.live_naukari.qa.util.Data_Utility;
import com.live_naukari.qa.util.Helper_s;

public class Page_1_Dashboard_All_Modules_checking extends Base_Page {

    public Page_1_Dashboard_All_Modules_checking(WebDriver driver) {
	super(TestBase.getDriver());

    }

    Helper_s helper = new Helper_s();
    Data_Utility du = new Data_Utility();
    @FindBy(xpath = "//div[@class='nI-gNb-drawer__icon']")
    WebElement profile_click;

    @FindBy(xpath = "//div[@class='nI-gNb-info__heading']/span")
    WebElement check_user_names;

    @FindBy(xpath = "//div[@class='nI-gNb-info__sub-heading']")
    WebElement Title_job_name;

    @FindBy(xpath = "//a[@class='nI-gNb-info__sub-link']")
    WebElement view_and_update_profile;

    @FindBy(xpath = "//span[@class='fullname']") // user differ id change
    WebElement user_full_name;

    @FindBy(xpath = "//input[@class='dummyUpload typ-14Bold']") // user differ id change
    WebElement user_upload_resume;

    @FindBy(xpath = "//div[@class='resume-name-inline']") // user differ id change
    WebElement user_file_name;

    @FindBy(xpath = "//div[@class='updateOn typ-14Regular']") // user differ id change
    WebElement update_On;

    public void profile_Modules_checking() throws InterruptedException, IOException, AWTException {

	Thread.sleep(1000);
	helper.waitFor(profile_click);
	helper.highLightElement(driver, profile_click);
	profile_click.click();
	Reporter.log("<B><font color = 'blue'>Step1 .</font></B></B> Clicked on User_profile ");
	Assert.assertTrue(true, "Failed to Click on  User profile");

	helper.waitFor(check_user_names);
	helper.highLightElement(driver, check_user_names);
	String u = check_user_names.getText();
	System.out.println(" User Name is :-- " + u);
	Reporter.log("<B><font color = 'blue'>Step2 .</font></B></B> checked on User Name ");
	Assert.assertEquals(u, check_user_names.getText(), "Failed to match the User Name");

	helper.waitFor(Title_job_name);
	helper.highLightElement(driver, Title_job_name);
	Title_job_name.click();
	Reporter.log("<B><font color = 'blue'>Step3 .</font></B></B> Clicked on User_profile ");
	Assert.assertEquals(Title_job_name.getText(), Title_job_name.getText(), "Displayed job title does not match!");

	helper.waitFor(view_and_update_profile);
	helper.highLightElement(driver, view_and_update_profile);
	view_and_update_profile.click();

	Thread.sleep(1000);
	driver.navigate().refresh();
	Thread.sleep(1000);

	helper.waitFor(user_full_name);
	helper.highLightElement(driver, user_full_name);
	helper.explicitlyWait(user_full_name);
	System.out.println("Naukari_user_Full_Name is :--" + user_full_name.getText());

	Reporter.log(
		"<B><font color = 'blue'>Step4 .</font></B></B> after click on view_and_update_profile link succfuling re-direct or  navigation! Profile page ");
	Assert.assertEquals(u, user_full_name.getText(),
		" after click on view_and_update_profile link Un-successfuling cann't  re-direct or cann't  navigation! Profile page ");

	System.out.println("Title of Page :- " + driver.getTitle());

	helper.waitFor(user_upload_resume);
	helper.highLightElement(driver, user_upload_resume);
	user_upload_resume.click();
	helper.uploadFile(
		"C:\\Users\\MADHU E\\eclipse-workspace\\test\\LIVE_NAUKARI_MADHU\\src\\test\\resources\\Madhu_Erukula_QA_Automation_Tester_Resume.docx");

	Thread.sleep(1000);
	helper.highLightElement(driver, user_file_name);
	String file_name = user_file_name.getText();
	System.out.println("User Uploading file name is :-- " + file_name);

	helper.highLightElement(driver, update_On);
	String up_dated_on_time = update_On.getText();
	System.out.println("User Uploading file on Time-Date :-- " + up_dated_on_time);

	Reporter.log(
		"<B><font color = 'blue'>Step5 .</font></B></B> User  click  Updated resume and uploading resume ");
	Assert.assertEquals(file_name, file_name,
		" after click on   Updated resume and uploading resume   cann't file  Uploading ");

    }

}

package com.live_naukari.test_cases.user_side;

import org.testng.annotations.Test;

import com.live_naukari.qa.base.TestBase;
import com.live_naukari.qa.pages.userside.Page_1_Dashboard_All_Modules_checking;

public class Test_1_All_Naukari_Tests extends TestBase {
    @Test(priority = 0)
    public void User_profile_Module_Testcases1() throws Exception {

	Page_1_Dashboard_All_Modules_checking nt = new Page_1_Dashboard_All_Modules_checking(getDriver());
	nt.profile_Modules_checking();

    }

    @Test(priority = 1, invocationCount = 5) // more time s
    public void User_profile_Module_Testcases2() throws Exception {

	Page_1_Dashboard_All_Modules_checking nt = new Page_1_Dashboard_All_Modules_checking(getDriver());
	nt.profile_Modules_checking();

    }

    @Test(priority = 1, invocationCount = 4, threadPoolSize = 2) // parallel run
    public void User_profile_Module_Testcases3() throws Exception {

	Page_1_Dashboard_All_Modules_checking nt = new Page_1_Dashboard_All_Modules_checking(getDriver());
	nt.profile_Modules_checking();

    }

    @Test(enabled = false) // not executed
    public void User_profile_Module_Testcases4() throws Exception {

	Page_1_Dashboard_All_Modules_checking nt = new Page_1_Dashboard_All_Modules_checking(getDriver());
	nt.profile_Modules_checking();

    }

}

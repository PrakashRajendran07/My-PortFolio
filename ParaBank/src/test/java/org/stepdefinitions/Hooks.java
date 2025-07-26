package org.stepdefinitions;
import org.base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BaseClass {

    @Before
    public void setup() {
        getDriver(); 
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}

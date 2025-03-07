package com.rnr.app.suites;

import com.rnr.app.WeatherApplicationIT;
import com.rnr.app.controller.WeatherControllerTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SuiteDisplayName("Weather Suite Test")
@SelectPackages({
        "com.rnr.app.data"
})
@SelectClasses({
        WeatherControllerTest.class,
        WeatherApplicationIT.class
})
@SpringBootTest
public class WeatherTestSuite {
}

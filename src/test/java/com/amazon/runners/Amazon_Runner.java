package com.amazon.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"rerun:target/rerun.txt",
                "html:target/cucumberReport.html",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber.json",
                "pretty",
        },
        features = "src/test/resources/features",
        glue = "com/amazon/step_definitions",
        tags = "",
        stepNotifications = true,
        dryRun = false,
        publish = false
)
public class Amazon_Runner {
}

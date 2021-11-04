package com.example.jobagapi.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"pretty","json:target/cucumber.json"},
        features="classpath:features"
)
public class CucumberRunnerTest {
}

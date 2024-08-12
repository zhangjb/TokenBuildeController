package org.cics.demo2;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String cloudHome;

    // getters and setters
    public String getcloudHome() {
        return cloudHome;
    }

    public void setcloudHome(String cloudHome) {
        this.cloudHome = cloudHome;
    }

}

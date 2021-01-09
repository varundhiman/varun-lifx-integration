package com.gfs.driverApps.warRoomNotification.jenkins;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.JsonNode;
import com.gfs.driverApps.warRoomNotification.util.RestUtil;

public class JenkinsConfig {

    @Value("${sonar.user}")
    String username;

    @Value("${sonar.password}")
    String password;

    @Bean(name = "jenkinsDCIQuery")
    public RestUtil<JsonNode> jenkinsDCIQuery(@Value("${jenkins.dci.url}") final String jenkinsUrl) {
        return jenkinsQuery(jenkinsUrl);
    }

    @Bean(name = "jenkinsTCIQuery")
    public RestUtil<JsonNode> jenkinsTCIQuery(@Value("${jenkins.tci.url}") final String jenkinsUrl) {
        return jenkinsQuery(jenkinsUrl);
    }

    @Bean(name = "jenkinsTPIQuery")
    public RestUtil<JsonNode> jenkinsTPIQuery(@Value("${jenkins.transpro.url}") final String jenkinsUrl) {
        return jenkinsQuery(jenkinsUrl);
    }

    private RestUtil<JsonNode> jenkinsQuery(String url) {
        final RestUtil<JsonNode> jenkinsQuery = new RestUtil<>();
        jenkinsQuery.setAuthCreds(null, username, password);
        jenkinsQuery.setUrl(url);
        jenkinsQuery.setTypeOfT(JsonNode.class);
        return jenkinsQuery;

    }

}

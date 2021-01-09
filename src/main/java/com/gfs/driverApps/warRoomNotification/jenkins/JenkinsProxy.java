package com.gfs.driverApps.warRoomNotification.jenkins;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.gfs.driverApps.warRoomNotification.util.RestUtil;

public class JenkinsProxy {

    public static String JENKINS_BUILD_STATUS_PARAM = "color";

    @Autowired
    RestUtil<JsonNode> jenkinsDCIQuery;

    @Autowired
    RestUtil<JsonNode> jenkinsTCIQuery;

    @Autowired
    RestUtil<JsonNode> jenkinsTPIQuery;

    // https://stackoverflow.com/questions/32106703/jenkins-job-status-customize-color-status-of-job
    String findDCIJenkinsBuildColor() {
        return jenkinsDCIQuery.getQueryResult().get("JENKINS_BUILD_STATUS_PARAM").asText();
    }

}

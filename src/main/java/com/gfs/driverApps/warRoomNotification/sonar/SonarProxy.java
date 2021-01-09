package com.gfs.driverApps.warRoomNotification.sonar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gfs.driverApps.warRoomNotification.sonar.model.SonarProjectStatus;
import com.gfs.driverApps.warRoomNotification.util.RestUtil;

@Component
public class SonarProxy {

    private static final String PROJECT_KEY_PARAM = "projectKey";

    @Autowired
    RestUtil<SonarProjectStatus> sonarQuery;

    public SonarProjectStatus findProjectHealth(String projectKey) {
        sonarQuery.setQueryParams(PROJECT_KEY_PARAM, projectKey);
        return sonarQuery.getQueryResult();
    }

}

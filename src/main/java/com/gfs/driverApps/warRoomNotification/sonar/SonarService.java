package com.gfs.driverApps.warRoomNotification.sonar;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gfs.driverApps.warRoomNotification.sonar.model.SonarProjectStatus;

@Component
public class SonarService {

    @Value("${sonar.projects}")
    private String[] sonarProjects;

    @Autowired
    SonarProxy sonarProxy;

    public Boolean isSonarQualityGatePassed() {
        return Arrays.stream(sonarProjects).map(this::findProjectHealthStatus).reduce(Boolean::logicalAnd).get();
    }

    public Boolean findProjectHealthStatus(String sonarProjKey) {
        final SonarProjectStatus sonarProjStatus = sonarProxy.findProjectHealth(sonarProjKey);
        if (sonarProjStatus.getStatus().equalsIgnoreCase("NONE")) {
            return true;
        }
        return false;

    }

}

package com.gfs.driverApps.warRoomNotification;

import static com.gfs.driverApps.warRoomNotification.Status.OK;
import static com.gfs.driverApps.warRoomNotification.Status.SONAR_FAILURE;
import static com.gfs.driverApps.warRoomNotification.Status.UNREACHABLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gfs.driverApps.warRoomNotification.device.DeviceService;
import com.gfs.driverApps.warRoomNotification.sonar.SonarService;

@Component
public class NotificationProcessor {

    @Autowired
    SonarService sonarService;

    @Autowired
    DeviceService deviceService;

    public void processAllSystems() {

        processSonarReports();
        // processJenkinsBuilds();

        // updateDeviceState(null, null);

    }

    private void processJenkinsBuilds() {
        // TODO Auto-generated method stub

    }

    private void updateDeviceState(Status buildStatus, Status sonarStatus) {
        deviceService.changeDeviceState(Status.OK);
    }

    public Status processSonarReports() {
        try {
            final boolean sonarStatus = sonarService.isSonarQualityGatePassed();
            return sonarStatus ? OK : SONAR_FAILURE;
        } catch (final Exception e) {
            return UNREACHABLE;
        }
    }

}
